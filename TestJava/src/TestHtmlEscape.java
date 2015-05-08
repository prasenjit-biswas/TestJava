import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

public class TestHtmlEscape {
	public static void main(String[] args) {
		String inputHtml = "<Hello> &lt;Hello2&gt;";
		System.out.println(" inputHtml : "+inputHtml);
		String escapeHtml = escapeHtml4(inputHtml);
		System.out.println(" escapeHtml : "+escapeHtml);
	}
}

