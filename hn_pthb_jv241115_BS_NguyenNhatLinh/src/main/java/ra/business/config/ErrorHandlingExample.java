package ra.business.config;

public class ErrorHandlingExample {

    public static void executeWithErrorHandling() {
        try {
            // Đoạn mã có khả năng phát sinh lỗi
            riskyOperation();
        } catch (Exception e) {
            // Ghi lại thông báo lỗi
            System.err.println("An error occurred: " + e.getMessage());

            // In ra stack trace để biết vị trí lỗi
            System.err.println("Error Details:");
            for (StackTraceElement element : e.getStackTrace()) {
                System.err.println("Class: " + element.getClassName() +
                        ", Method: " + element.getMethodName() +
                        ", File: " + element.getFileName() +
                        ", Line: " + element.getLineNumber());
            }

            // Tùy chọn: Ghi lỗi vào log (nếu cần)
            logError(e);
        }
    }

    // Ví dụ một phương thức có thể gây lỗi
    private static void riskyOperation() throws Exception {
        // Dưới đây là ví dụ về lỗi chia cho 0
        int number1 = 10, number2 = 0;
        int result = number1 / number2; // Lỗi xảy ra tại đây
        System.out.println("Result: " + result);
    }

    // Ghi lại lỗi vào file log (hoặc hệ thống logging khác)
    private static void logError(Exception e) {
        // Giả sử ghi log ra màn hình (hoặc ghi vào file/log server)
        System.err.println("Logged error: " + e.getMessage());
    }

    public static void main(String[] args) {
        // Gọi hàm thực thi với xử lý lỗi
        executeWithErrorHandling();
    }
}
