package one.chapter12.slider;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @Auther lovely
 * @Create 2020-02-25 9:24
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description A frame with many sliders and a text field to show slider values
 */
public class SliderFrame extends JFrame {
    private JPanel sliderPanel;
    private JTextField textField;
    private ChangeListener listener;


    public SliderFrame() {
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout());

        //common listener for all sliders
        listener = event ->{
            //update text field when the slider value change
            JSlider source = (JSlider) event.getSource();
            textField.setText("" + source.getValue());
        };

        //add a plain slider
        JSlider slider = new JSlider();
        addSlider(slider, "Plain");

        //add a slider with major and minor ticks
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Ticks");

        //add a slider with no track
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTrack(false);
        addSlider(slider, "No track");

        //add an inverted slider
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setInverted(true);
        addSlider(slider, "Inverted");

        //add a slider with alphabetic labels
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);

        Dictionary<Integer, Component> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("A"));
        labelTable.put(20, new JLabel("B"));
        labelTable.put(40, new JLabel("C"));
        labelTable.put(60, new JLabel("D"));
        labelTable.put(80, new JLabel("E"));
        labelTable.put(100, new JLabel("F"));
        slider.setLabelTable(labelTable);


        addSlider(slider, "Custom labels");

        //add s slider with icon labels
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMinorTickSpacing(20);
        slider.setMajorTickSpacing(20);

        labelTable = new Hashtable<>();

        //add card images
        labelTable.put(0, new JLabel("ImageA"));
        labelTable.put(20, new JLabel("ImageB"));
        labelTable.put(40, new JLabel("ImageC"));
        labelTable.put(60, new JLabel("ImageD"));
        labelTable.put(80, new JLabel("ImageE"));
        labelTable.put(100, new JLabel("ImagesF"));

        slider.setLabelTable(labelTable);
        addSlider(slider, "Icon labels");
        //add the text field that displays the slider value
        textField = new JTextField();
        add(sliderPanel, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        pack();
    }

    /**
     * adds a slider to the slider panel and hooks up the listener
     * @param s the slider
     * @param description the slider description
     */
    private void addSlider(JSlider s, String description) {
        s.addChangeListener(listener);
        JPanel panel = new JPanel();
        panel.add(s);
        panel.add(new JLabel(description));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = sliderPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.WEST;
        sliderPanel.add(panel, gbc);
    }

}
