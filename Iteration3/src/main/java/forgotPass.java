import java.util.Scanner;

public class forgotPass {
    private Scanner scanner;

    public forgotPass() {
        scanner = new Scanner(System.in);
    }

    public void resetPassword() {
        System.out.println("Reset Password");
        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine();

        if (newPassword.equals(confirmPassword)) {
            // Here you can implement your logic to save the new password
            System.out.println("Password saved successfully.");
        } else {
            System.out.println("Passwords do not match.");
        }
    }

    public static void main(String[] args) {
    	forgotPass forgotPassword = new forgotPass();
        forgotPassword.resetPassword();
    }
}