import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;

class HtmlConverterTest {
    @Test
    void testConvertToHtml() {
        Matcher matcher1 = Pattern.compile(HtmlConverter.TITLE).matcher("$I am a title$");
        Matcher matcher2 = Pattern.compile(HtmlConverter.HEADER).matcher("#I am a header#");
        Matcher matcher3 = Pattern.compile(HtmlConverter.LIST).matcher("- I am a list");
        Matcher matcher4 = Pattern.compile(HtmlConverter.URL).matcher("[[https://www.example.com][Click me!]]");
        Matcher matcher5 = Pattern.compile(HtmlConverter.BOLD).matcher("**Make me bold**");
        Matcher matcher6 = Pattern.compile(HtmlConverter.ITALICS).matcher("*Make me italics*");
        Matcher matcher7 = Pattern.compile(HtmlConverter.PARAGRAPH).matcher("I am just a random text");

        if (matcher1.find())
            assertEquals(HtmlConverter.convertToHTML(HtmlConverter.TITLE, matcher1), "<title>I am a title</title>");

        if (matcher2.find())
            assertEquals(HtmlConverter.convertToHTML(HtmlConverter.HEADER, matcher2), "<h1>I am a header</h1>");

        if (matcher3.find())
            assertEquals(HtmlConverter.convertToHTML(HtmlConverter.LIST, matcher3), "<li>I am a list</li>");

        if (matcher4.find())
            assertEquals(HtmlConverter.convertToHTML(HtmlConverter.URL, matcher4),
                    "<a href=\"https://example.com\" target=\"_blank\">Click me!</a>");

        if (matcher5.find())
            assertEquals(HtmlConverter.convertToHTML(HtmlConverter.BOLD, matcher5), "<b>Make me bold</b>");

        if (matcher6.find())
            assertEquals(HtmlConverter.convertToHTML(HtmlConverter.ITALICS, matcher6), "<i>Make me italics</i>");

        if (matcher7.find())
            assertEquals(HtmlConverter.convertToHTML(HtmlConverter.PARAGRAPH, matcher7),
                    "I am just a random text</br>");
    }

    @Test
    void testGetHTMLFileName() {
        assertEquals(HtmlConverter.getHTMLFileName("abhd.txt"), "abhd.html");
        assertEquals(HtmlConverter.getHTMLFileName("name.lastname.txt"), "name.lastname.html");
        assertEquals(HtmlConverter.getHTMLFileName("random.txt"), "random.html");
        assertEquals(HtmlConverter.getHTMLFileName("index.txt"), "index.html");
    }
}
