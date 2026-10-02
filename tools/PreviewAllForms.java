import java.awt.*; import java.awt.image.*; import java.io.*; import java.lang.reflect.*; import java.util.*; import javax.swing.*; import javax.imageio.ImageIO;
public class PreviewAllForms {
 static ArrayList<BufferedImage> pictures = new ArrayList<>(); static ArrayList<String> titles = new ArrayList<>();
 public static void main(String[] args) throws Exception {
  UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
  new File(args[0]).mkdirs();
  SwingUtilities.invokeAndWait(() -> {
   for(int i=1;i<args.length;i++) { JFrame f=null; try {
    f=(JFrame)Class.forName(args[i]).getConstructor().newInstance(); f.addNotify(); f.validate();
    if(f.getIconImage()==null) throw new AssertionError("Missing app icon");
    ArrayList<Component> cs=new ArrayList<>();
    for(Field field:f.getClass().getDeclaredFields()) { field.setAccessible(true); Object o=field.get(f); if(o instanceof Component c) {
     cs.add(c); if(c.getWidth()<=0 || c.getHeight()<=0) throw new AssertionError(field.getName()+": zero size");
     if(c instanceof JLabel l && l.getText()!=null && !l.getText().startsWith("<html>")) { int required=l.getFontMetrics(l.getFont()).stringWidth(l.getText()); if(required>l.getWidth()) throw new AssertionError(field.getName()+": text clipped "+required+">"+l.getWidth()); }
     if(c instanceof JButton b) { int required=b.getFontMetrics(b.getFont()).stringWidth(b.getText()); if(required>b.getWidth()-8) throw new AssertionError(field.getName()+": button text clipped "+required+">"+b.getWidth()); }
     if(c instanceof JLabel l && l.getIcon()!=null && (l.getIcon().getIconWidth()>l.getWidth() || l.getIcon().getIconHeight()>l.getHeight())) throw new AssertionError("Clipped logo");
     if(c instanceof JTable t && t.getRowCount()!=0) throw new AssertionError("Fake table rows");
    } }
    for(int a=0;a<cs.size();a++) for(int b=a+1;b<cs.size();b++) { Component ca=cs.get(a), cb=cs.get(b); if(ca.getParent()!=null && ca.getParent()==cb.getParent() && ca.getBounds().intersects(cb.getBounds())) throw new AssertionError("Overlapping "+ca.getClass()+" / "+cb.getClass()); }
    Container content=f.getContentPane(); BufferedImage img=new BufferedImage(content.getWidth(),content.getHeight(),BufferedImage.TYPE_INT_RGB); Graphics2D g=img.createGraphics(); content.printAll(g); g.dispose();
    ImageIO.write(img,"png",new File(args[0],f.getClass().getSimpleName()+".png")); pictures.add(img); titles.add(args[i]);
    System.out.println("PASS "+args[i]+" "+img.getWidth()+"x"+img.getHeight());
   } catch(Exception e) { throw new RuntimeException(args[i],e); } finally {if(f!=null) f.dispose();} }
  });
  for(int start=0; start<pictures.size(); start+=4) { BufferedImage page=new BufferedImage(1600,1500,BufferedImage.TYPE_INT_RGB); Graphics2D g=page.createGraphics(); g.setColor(new Color(230,232,235)); g.fillRect(0,0,1600,1500); for(int k=0;k<4 && start+k<pictures.size();k++){BufferedImage img=pictures.get(start+k); int x=(k%2)*800,y=(k/2)*750; double scale=Math.min(770.0/img.getWidth(),700.0/img.getHeight()); g.setColor(Color.BLACK); g.setFont(new Font("Segoe UI",Font.BOLD,15)); g.drawString(titles.get(start+k),x+12,y+22); g.drawImage(img,x+12,y+35,(int)(img.getWidth()*scale),(int)(img.getHeight()*scale),null);} g.dispose(); ImageIO.write(page,"png",new File(args[0],"review-"+(start/4+1)+".png")); }
 }
}
