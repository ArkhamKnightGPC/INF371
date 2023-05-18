import java.awt.Font;
import typo.*;

public class Test {
    static void test2() {
        Font f = new Font("SansSerif", Font.PLAIN, 70);
        Glyph g = new Glyph(f, 'g');
        System.out.println(g);
    }

    static void test3() {
        Font f = new Font("SansSerif", Font.PLAIN, 70);
        Glyph g = new Glyph(f, 'g');
        System.out.println(g);
        new Page(g, 150, 150);
    }

    static void test5(){
        Space s = new Space(2, 3);
        FixedSpace fs = new FixedSpace(5);
        Font f = new Font("SansSerif", Font.PLAIN, 70);
        RelativeSpace rs = new RelativeSpace(35.0/f.getSize(), f);
        System.out.println(s);
        System.out.println(fs);
        System.out.println(rs);
    }

    static void test6(){
        Group g = new TestableGroup();
        Group subGroup = new TestableGroup();

        Space s = new Space(2, 3);
        FixedSpace fs = new FixedSpace(5);
        subGroup.add(s); subGroup.add(fs);

        g.add(subGroup);
        Font f = new Font("SansSerif", Font.PLAIN, 70);
        RelativeSpace rs = new RelativeSpace(35.0/f.getSize(), f);
        g.add(rs);

        System.out.println(g);
    }

    static void test7a() {
        Hbox h = new Hbox();
        System.out.println(h);
        Font f = new Font("SansSerif", Font.PLAIN, 40);
        h.add(new Glyph(f, 'a'));
        System.out.println(h);
        h.add(new Space(2., 3.));
        System.out.println(h);
    }
}
