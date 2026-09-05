# Library Management System

Ứng dụng quản lý thư viện chạy trên dòng lệnh, được xây dựng bằng Java 17 và Maven. Dữ liệu được lưu trong bộ nhớ trong thời gian chương trình hoạt động.

## Chức năng

- Thêm và xem danh sách sách.
- Tìm sách theo tiêu đề, không phân biệt chữ hoa và chữ thường.
- Thêm và xem danh sách thành viên.
- Mượn và trả sách.
- Ngăn một cuốn sách đang được mượn tiếp tục được cho mượn.
- Xem các lượt mượn chưa được trả.
- Kiểm tra dữ liệu đầu vào cơ bản và ngăn ID trùng lặp.

## Công nghệ

- Java 17
- Apache Maven
- JUnit 5

## Cấu trúc project

```text
src/
├── main/java/com/library/
│   ├── Main.java                 # Giao diện dòng lệnh
│   ├── exception/                # Các exception nghiệp vụ
│   ├── models/                   # Book, Member và BorrowRecord
│   ├── services/                 # Xử lý nghiệp vụ thư viện
│   └── utils/                    # Kiểm tra dữ liệu đầu vào
└── test/java/com/library/
    └── LibraryServiceTest.java   # Kiểm thử nghiệp vụ
```

## Yêu cầu

Kiểm tra máy đã cài Java và Maven:

```bash
java -version
mvn -version
```

Java cần có phiên bản 17 trở lên.

## Build và kiểm thử

Tại thư mục gốc của project, chạy:

```bash
mvn test
```

Đóng gói ứng dụng:

```bash
mvn package
```

## Chạy ứng dụng

Biên dịch rồi khởi động lớp chính:

```bash
mvn compile
java -cp target/classes com.library.Main
```

Sau khi chương trình chạy, nhập số tương ứng với chức năng trên menu. Ví dụ, chọn `1` để thêm sách, `4` để thêm thành viên và `6` để mượn sách.

## Quy tắc dữ liệu

- ID sách và ID thành viên không được để trống và không được trùng nhau, kể cả khi khác chữ hoa/chữ thường.
- Tên sách, tác giả và tên thành viên phải có ít nhất hai ký tự.
- Email phải chứa ký tự `@`.
- Một cuốn sách chỉ có thể có một lượt mượn đang hoạt động.
- Khi trả sách, ID sách và ID thành viên phải khớp với lượt mượn đang hoạt động.

## Hạn chế hiện tại

- Dữ liệu chưa được lưu vào file hoặc cơ sở dữ liệu; dữ liệu sẽ mất khi thoát chương trình.
- Ứng dụng chưa hỗ trợ chỉnh sửa hoặc xóa sách và thành viên.
- Kiểm tra định dạng email mới ở mức cơ bản.
