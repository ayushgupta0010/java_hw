import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HtmlConverter {
    final static String TITLE = "\\$([\\w\\W]+)\\$";
    final static String HEADER = "#([\\w\\W]+)#";
    final static String LIST = "-\\s([\\w\\W]+)";
    final static String URL = "\\[\\[([https?|www]\\S+)\\]\\[(\\w+)\\]\\]";
    final static String BOLD = "\\*\\*(\\w+)\\*\\*";
    final static String ITALICS = "\\*(\\w+)\\*";
    final static String PARAGRAPH = "([\\w\\W]+)";

    public static void main(String[] args) throws IOException, FileNotFoundException {
        String txtFile = getFileName();

        Scanner fileScanner = new Scanner(new File(txtFile));

        FileWriter fileWriter = new FileWriter(getHTMLFileName(txtFile));
        fileWriter.write("<html>\n<body>\n");

        boolean isUlOn = false;

        String regex[] = { TITLE, HEADER, LIST, URL, BOLD, ITALICS, PARAGRAPH };

        while (fileScanner.hasNextLine()) {
            String temp = fileScanner.nextLine();
            for (int i = 0; i < regex.length; ++i) {
                if (temp.isBlank()) {
                    fileWriter.write((isUlOn ? "</ul>\n" : "") + "<p>\n");
                    if (isUlOn)
                        isUlOn = false;
                    break;
                }

                Matcher matcher = Pattern.compile(regex[i]).matcher(temp);
                if (matcher.find()) {
                    if (regex[i] == LIST && !isUlOn) {
                        fileWriter.write("<ul>\n");
                        isUlOn = true;
                    } else if (regex[i] != LIST && isUlOn) {
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
        String fileName = "";
        Scanner sc = new Scanner(System.in);
        int index;

        do {
            System.out.print("Enter the file name ending in '.txt': ");
            fileName = sc.nextLine().trim();
            index = fileName.lastIndexOf('.');
        } while (index == -1 || !fileName.substring(index).equals(".txt"));

        return fileName;
    }

    public static String getHTMLFileName(String txtFile) {
        return txtFile.substring(0, txtFile.lastIndexOf('.')) + ".html";
    }

    public static String convertToHTML(String regex, Matcher matcher) {
        switch (regex) {
            case TITLE:
                return "<title>" + matcher.group(1) + "</title>";

            case HEADER:
                return "<h1>" + matcher.group(1) + "</h1>";

            case LIST:
                return "<li>" + matcher.group(1) + "</li>";

            case URL:
                return String.format("<a href=\"%s\" target=\"_blank\">%s</a>", matcher.group(1), matcher.group(2));

            case ITALICS:
                return "<i>" + matcher.group(1) + "</i>";

            case BOLD:
                return "<b>" + matcher.group(1) + "</b>";

            case PARAGRAPH:
                return matcher.group(1) + "</br>";

            default:
                return matcher.group();
        }

    }
}
