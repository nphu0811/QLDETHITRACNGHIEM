package trangchu;

import java.awt.*;
import javax.swing.*;

// Co giãn nội dung theo khung bên phải; form quá rộng/cao vẫn có thể cuộn.
class NoiDungForm extends JPanel implements Scrollable {
    NoiDungForm(Container noiDung) {
        super(new BorderLayout());
        add(noiDung, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle rect, int direction, int sign) {
        return 20;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle rect, int direction, int sign) {
        return direction == SwingConstants.VERTICAL ? rect.height : rect.width;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return getParent() != null && getParent().getWidth() >= getMinimumSize().width;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return getParent() != null && getParent().getHeight() >= getPreferredSize().height;
    }
}
