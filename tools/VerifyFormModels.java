import javax.xml.parsers.*; import org.w3c.dom.*; import java.io.*; import java.beans.*; import java.lang.reflect.*;
public class VerifyFormModels {
 public static void main(String[] args) throws Exception {
  int count=0;
  for(String path:args) { Document d=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path)); NodeList props=d.getElementsByTagName("Property"); for(int i=0;i<props.getLength();i++){ Element p=(Element)props.item(i); String editor=p.getAttribute("editor"); if(editor.endsWith("ComboBoxModelEditor") || editor.endsWith("TableModelEditor")) { Object ed=Class.forName(editor).getConstructor().newInstance(); Method read=ed.getClass().getMethod("readFromXML",Node.class); for(Node n=p.getFirstChild();n!=null;n=n.getNextSibling()) if(n instanceof Element){ try{read.invoke(ed,n); count++;}catch(InvocationTargetException e){ throw new RuntimeException(path+": "+editor,e.getCause());} break; } } } }
  System.out.println("PASS: "+count+" combo/table models loaded by native NetBeans property editors.");
 }
}
