package utility;

public class TestSecurityManager {
	public static void main(String[] args) {
		SecurityManager sm = new SecurityManager();
		sm.checkPackageAccess("utility");
	}
}
