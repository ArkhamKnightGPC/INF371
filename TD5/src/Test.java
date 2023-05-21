import java.awt.Font;
import typo.*;

public class Test {

    static void test3() {
        Font f = new Font("Serif", Font.PLAIN, 70);
        Glyph g = new Glyph(f, 'g');
        System.out.println(g);
        new Page(g, 150, 150);
    }

    static Hbox lineFromString(Font f, String s) {
        Hbox line = new Hbox();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                line.add(new RelativeSpace(0.5, f));
            else {
                line.add(new Glyph(f, c));
                if (i < s.length() - 1 && s.charAt(i + 1) != ' ')
                    line.add(new FixedSpace(2));
            }
        }
        return line;
    }

    final static Box hfill = new Space(0, Double.POSITIVE_INFINITY);

    static Vbox fromString(Font f, String s) {
        Vbox text = new Vbox(5);
        String[] lines = s.split("\n");

        for (int i = 0; i < lines.length; ++i) {
            Hbox line = lineFromString(f, lines[i]);

            if (i + 1 == lines.length)
                line.add(hfill);
            text.add(line);
        }
        return text;
    }

    static void test7b() {
        Font f = new Font("SansSerif", Font.PLAIN, 40);
        Box t = lineFromString(f, "Typographie sans peine");
        System.out.println(t);
        new Page(t, 450, 150);
    }

    static void test8a() {
        Font f = new Font("Serif", Font.PLAIN, 40);
        Box t = fromString(f,
                "L'homme n'est qu'un\n" +
                        "roseau, le plus faible\n" +
                        "de la nature ; mais\n" +
                        "c'est un roseau pensant.");
        new Page(t, 450);
        System.out.println(t);
    }

    static void test8b() {
        Font f = new Font("Serif", Font.PLAIN, 30);
        Font lettrinef = new Font("Serif", Font.PLAIN, 120);
        Vbox t = new Vbox(5);
        Hbox h = new Hbox();
        h.add(new Glyph(lettrinef, 'L'));
        h.add(new Space(3, 1));
        Vbox l = new Vbox(5);
        l.add(lineFromString(f, "'homme n'est qu'un roseau, le"));
        l.add(lineFromString(f, "plus faible de la nature ; mais"));
        l.add(lineFromString(f, "c'est un roseau pensant. Il ne"));
        h.add(l);
        t.add(h);
        t.add(lineFromString(f, "faut pas que l'univers entier s'arme"));
        t.add(lineFromString(f, "pour l'écraser : une vapeur, une"));
        t.add(fromString(f, "goutte d'eau, suffit pour le tuer."));
        new Page(t, 500);
    }
}
