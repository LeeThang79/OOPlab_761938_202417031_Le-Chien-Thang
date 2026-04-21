package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;
import java.util.List;

public class Aims {
    public static void main(String[] args) {
            // 1. Tạo một danh sách kiểu Media (Lớp cha)
            List<Media> mediae = new ArrayList<Media>();

            // 2. Tạo các đối tượng cụ thể (Sách, CD, DVD)
            // Lưu ý: Các tham số truyền vào phải khớp với Constructor bạn đã viết
            Media dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
            Media book = new Book("Java Programming", "Education", 29.99f);
            Media cd = new CompactDisc("Greatest Hits", "Music", "Various Artists", "Producer A", 45, 15.0f);

            // 3. Thêm tất cả vào danh sách Mediae
            mediae.add(dvd);
            mediae.add(book);
            mediae.add(cd);

            // 4. Duyệt danh sách và in ra (Tính Đa hình thể hiện ở đây)
            System.out.println("\n--- Danh sách Media trong cửa hàng ---");
            for (Media m : mediae) {
                // Dù m đang ở kiểu Media, nhưng Java sẽ tự gọi đúng toString() của DVD, Book hoặc CD
                System.out.println(m.toString());
            }
        }
    }

