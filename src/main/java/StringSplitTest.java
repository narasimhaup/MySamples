
public class StringSplitTest {

	public static void main(String[] args) {
		String s = null;
		String s1 = s+"re";
		System.out.println(s1);
		String token = "";
		StringBuffer valueBuffer = new StringBuffer();
		String[] iaTokens = token.trim().split(":");
		if(iaTokens.length == 3)
			valueBuffer.append(iaTokens[0] + ":" + iaTokens[1] + "~");
		valueBuffer
				.append(token.trim()+"~");
		System.out.println(valueBuffer.toString());
	}

}
