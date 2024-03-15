import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HtmlConverter {
    final static String HEADER = "#([\\w\\W]+)#";
    final static String LIST = "-\\s([\\w\\W]+)";
    final static String URL = "\\[\\[([https?|www]\\S+)\\]\\[(\\w+)\\]\\]";
    final static String PARAGRAPH = "([\\w\\W]+)";

    public static void main(String[] args) throws IOException, FileNotFoundException {
        String txtFile = getFileName();

        Scanner fileScanner = new Scanner(new File(txtFile));

        FileWriter fileWriter = new FileWriter(getHTMLFileName(txtFile));
        fileWriter.write("<html>\n<body>\n");

        boolean isUlOn = false;

        String regex[] = { HEADER, LIST, URL, PARAGRAPH };

        while (fileScanner.hasNextLine()) {
            String temp = fileScanner.nextLine();
            for (int i = 0; i < regex.length; ++i) {
                if(temp.isBlank()) {
                    fileWriter.write((isUlOn ? "</ul>\n" : "") + "<p>\n");
                    if(isUlOn)  isUlOn = false;
                    break;
                }

                Matcher matcher = Pattern.compile(regex[i]).matcher(temp);
                if (matcher.find()) {
                    if(regex[i] == LIST && !isUlOn) {
                        fileWriter.write("<ul>\n");
                        isUlOn = true;
                    } else if(regex[i] != LIST && isUlOn) {
                        fileWriter.write("</ul>\n");
                        isUlOn = false;
                    }

                    fileWriter.write((convertToHTML(regex[i], matcher) + '\n'));
                    break;
                }
            }
        }

        fileWriter.write("</body>\n</html>");
        fileWriter.close();
    }

    public static String getFileName() {
        System.out.print("Enter the file name: ");
        return new Scanner(System.in).nextLine();
    }

    public static String getHTMLFileName(String txtFile) {
        return txtFile.substring(0, txtFile.lastIndexOf('.')) + ".html";
    }

    public static String convertToHTML(String regex, Matcher matcher) {
        if (regex == HEADER) return "<h1>" + matcher.group(1) + "</h1>";
        if (regex == LIST) return "<li>" + matcher.group(1) + "</li>";
        if (regex == URL) return String.format("<a href=\"%s\" target=\"_blank\">%s</a>", matcher.group(1), matcher.group(2));
        if (regex == PARAGRAPH) return matcher.group(1) + "</br>";
        return matcher.group();
    }
}
