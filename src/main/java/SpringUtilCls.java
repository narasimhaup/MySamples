
public class SpringUtilCls {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String grp = org.springframework.util.StringUtils.trimAllWhitespace("WW Partner Organisation");
		grp = "SE_" + grp.toUpperCase() + "_PLANNER";
		System.out.println(grp);
	}

}
