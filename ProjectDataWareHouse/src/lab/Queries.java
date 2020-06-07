package lab;

import java.sql.SQLException;
import java.util.List;

public class Queries {
	public void createTable() throws SQLException {
		List<Object> title = new ReadFile().readData(
				"E:\\Tai_Lieu\\HK2-----3\\DatawareHouse\\DataFeedSpecification_17130110\\DataFeedSpecification_17130110.xlsx")
				.get(0);

		List<Object> infor = new ReadFile().readData(
				"E:\\Tai_Lieu\\HK2-----3\\DatawareHouse\\DataFeedSpecification_17130110\\DataFeedSpecification_17130110.xlsx")
				.get(1);

		DBConnection connect = new DBConnection();

		for (int i = 0; i < infor.size(); i++) {
			Object ob = infor.get(i).getClass();
			if (ob.equals(Integer.class)) {
				System.out.println("int");

			}
			if (ob.equals(String.class)) {
				System.out.println("STRING");

			}
		}
//
//		String text1 = "Hello java regex 2-12-2018, hello world 12/12/2018";
//        Pattern pattern = Pattern.compile("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}");
//        Matcher matcher = pattern.matcher(text1);
//          
//        System.out.println("Ngày tháng trong chuỗi text1: " + text1);
//        while (matcher.find()) {
//            System.out.println(text1.substring(matcher.start(), matcher.end()));
//        }
//          
//        String text2 = "2/12/2018";
//        String text3 = "12/12/aaaa";
//        pattern = Pattern.compile("^\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}$");
//        System.out.println("\nChuỗi " + text2 + " có định dạng ngày tháng: "
//                + pattern.matcher(text2).matches());
//          
//        System.out.println("Chuỗi " + text3 + " có định dạng ngày tháng: "
//                + pattern.matcher(text3).matches());
		
		
		
		String query = "create table information.INFORMATION ( id int(6) unsigned auto_increment primary key, fName varchar(30) not null);";
		connect.chonDuLieu(query);
//		for (Object string : infor) {
//			System.out.println(string);
//
//		}
	}

	public void definite(String str) {

	}

	public static void main(String[] args) throws SQLException {
		new Queries().createTable();
	}
}
