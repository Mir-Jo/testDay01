package TeamProject1.LibrarySystem;

import java.util.*;

public class Library extends XexistException{
	public static Scanner sc = new Scanner(System.in);
	public static LinkedHashMap<String, BookInfo> book = new LinkedHashMap<String, BookInfo>(); //LinkedHashMap(HashMap과 같지만 변수가 입력된 순서를 기억함)
	public static ArrayList<String> bookList = new ArrayList<String>();							//책의 번호를 담당할 ArrayList(번호 검색에서 이용)

	public static void main(String[] args) {
		System.out.println("============================================================================");
		System.out.println("                         도  서  관  리  시  스  템                     ");
		System.out.println("============================================================================");
		System.out.println();
		boolean flag = true;
		while (flag) {

			System.out.println("============================================================================");
			System.out.println("1.도서등록 | 2.도서목록 조회 | 3.도서정보 조회 | 4.도서정보 수정 | 5.도서정보 삭제 | 6. 종료");
			System.out.println("============================================================================");
			System.out.print("선택 => ");
			try {
				int category = sc.nextInt(); //Scanner로 기능을 선택할 변수 category 입력받음
				switch (category) {
				case 1: register(); break; 	//1. 도서등록
				case 2: inquiry(); break;	//2. 도서목록 조회
				case 3: info(); break;		//3. 도서정보 조회
				case 4: edit(); break;		//4. 도서정보 수정
				case 5:delete(); break;		//5. 도서정보 삭제
				case 6: System.out.println("프로그램 종료"); flag = false; break;	//6. 종료
				default: System.out.println("잘못 입력하셨습니다. 다시 입력해주세요."); break; //1~6 이외의 int값이 입력된 경우
				}
			} catch (InputMismatchException e) {System.out.println("잘못된 입력입니다. 메뉴로 돌아갑니다."); sc.nextLine();} //main메소드 안에서 지역변수와 다른 타입의 값이 Scanner로 들어오는 경우 catch
		}
	}	//main의 끝

	public static void register() { //1. 도서등록
		sc.nextLine();
		System.out.print("도서 등록 \n");
		System.out.print("제목을 입력해주세요 \n=>");
		String title = sc.nextLine();
		if (!book.containsKey(title)) {								//LinkedHashMap book에 입력된 제목과 같은 Key값이 존재하지 않을 경우
			System.out.print("저자를 입력해주세요\n=>");
			String author = sc.nextLine();
			System.out.print("출판사를 입력해주세요\n=>");
			String publisher = sc.nextLine();
			System.out.print("가격을 입력해주세요\n=>");
			int price = sc.nextInt();
			
			BookInfo a = new BookInfo(author, publisher, price);	//String title(제목)에 대한 정보 BookInfo객체 a 생성
			book.put(title, a);										//String title(제목)을 key로, BookInfo객체를 Value로 LinkedHashMap book에 입력
			bookList.add(title);									//책의 번호를 담당할 ArrayList bookList에 책 제목을 입력
			System.out.println("성공적으로 등록되었습니다.");
		} else { System.out.println("이미 등록된 책입니다."); }	//LinkedHashMap book에 입력된 제목과 같은 Key값이 존재할 경우
	}

	public static void inquiry() { //2. 도서목록 조회
		if (bookList.isEmpty()) { System.out.println("등록된 책이 없습니다."); }
		else {
			int i = 1;											//책 번호가 될 int 변수 i
			Iterator<String> keys = book.keySet().iterator();	//book은 LinkedHashMap이므로 들어온 순서는 기억하지만 배열처럼 특정할 수 없음(Iterator keys)로 나열
				while (keys.hasNext()) {						
					String key = keys.next();					
					System.out.println(i + ". " + key);			//hasNext()메소드를 이용해서 나열한 key값들을 순서대로 출력(book이 LinkedHashMap이므로 입력된 순서대로 출력됨)
					i++;
					}
			}
	}

	public static void info(){ //3. 도서정보 조회
		System.out.println("도서정보 조회 \n");
		boolean typeFlag = true; boolean infoFlag = true;			//typeFlag : 검색할 타입을 묻는 것을 반복하기 위한 | infoFlag : 선택한 타입의 검색을 반복하기 위한 Flag
		System.out.print("제목검색은 1, 번호검색은 2를 입력해주세요. \n=>");
		int typeNum = sc.nextInt();
		sc.nextLine();
			while(typeFlag) { 
				switch(typeNum) {
					case 1: while (infoFlag) {
							System.out.print("찾으시는 책의 제목을 입력해주세요. (메뉴로 돌아가시려면 x를 입력해주세요.) \n=> ");
								String srcTitle = sc.nextLine();											   //Scanner로 책의 제목 or 메뉴로의 복귀여부를 받는 변수 SrcTitle 선언
										if (srcTitle.equals("x")) {infoFlag = false; typeFlag = false; break;} //입력된 값이 "x"일 경우 while(typeFlag)밖으로 탈출 = 메소드 종료
										else if (book.containsKey(srcTitle)) {								   //srcTitle과 같은 Key값이 LinkedHashMap book에 있을 경우
											System.out.println("제목: " + srcTitle);							   //book의 srcTitle값에 해당하는 Value값 BookInfo의 정보 출력
											System.out.println("저자: " + book.get(srcTitle).getAuthor());
											System.out.println("출판사:" + book.get(srcTitle).getPublisher());
											System.out.println("가격: " + book.get(srcTitle).getPrice() + "원\n");
										} else {System.out.println("등록되어있지 않은 책입니다! \n");}				  //srcTitle과 같은 Key값이 book에 없을 경우
								}
					case 2: while (infoFlag) {
							System.out.print("찾으시는 책의 번호를 입력해주세요.(메뉴로 돌아가시려면 0을 입력해주세요.) \n=>"); 
								int srcNum = sc.nextInt();														//Scanner로 책의 번호 or 메뉴로의 복귀 여부를 받는 변수 srcNum 선언
									try {	
										if (srcNum == 0) {infoFlag = false; typeFlag = false; break;} 			//입력된 값이 0일경우 while(typeFlag)밖으로 탈출 = 메소드 종료
										else if (!bookList.isEmpty() && book.containsKey(bookList.get(srcNum - 1))) {//ArrayList bookList의 srcNum인덱스에 해당하는 제목 bookList.get(srcNum-1)
											System.out.println("제목: " + bookList.get(srcNum - 1));			 //containsKey()메소드를 활용하여 bookList.get(srcNum-1)에 해당하는 제목이 Key값으로 있을 경우
											System.out.println("저자: " + book.get(bookList.get(srcNum - 1)).getAuthor()); //book의 Key값 bookList.get(srcNum-1)에 해당하는 Value값 BookInfo의 정보 출력
											System.out.println("출판사:" + book.get(bookList.get(srcNum - 1)).getPublisher());
											System.out.println("가격: " + book.get(bookList.get(srcNum - 1)).getPrice() + "원 \n");
										} else {System.out.println(srcNum + "번에 해당하는 책이 없습니다! \n");}
									} catch (IndexOutOfBoundsException e) { System.out.println(srcNum + "번에 해당하는 책이 없습니다.\n");} 
									//srcNum값으로 bookList의 최대 index값보다 큰 값이 들어올 경우 발생하는 IndexOutOfBoundsException을 catch
								}
					default: System.out.println("메뉴로 돌아갑니다. \n"); typeFlag = false; //제목, 번호 선택에서 1과 2이 아닌 다른 int값이 입력될 경우 while(typeFlag)밖으로 탈출 = 메소드 종료
					}
				}
			}
	public static void edit(){ //4. 도서정보 수정
		sc.nextLine();
		boolean editFlag = true;
		System.out.println("도서정보 수정");
		while (editFlag) {
			System.out.print("수정하실 책의 제목을 입력해주세요. (메뉴로 돌아가시려면 x를 입력해주세요.) \n=>");
			String srcTitle = sc.nextLine();									//변경할 책의 제목을 찾기 위한 변수 srcTitle
			try {
				if (srcTitle.equals("x") && !(book.containsKey("x"))) { editFlag = false;} //srcTitle이 x일 경우 while(editFlag)밖으로 탈출 = 메소드 종료
				else if(book.containsKey("x") && srcTitle.equals("x")) { throw new XexistException();}
				else if (book.containsKey(srcTitle)) {								//book이 srcTitle이라는 Key값을 가지고 있을 경우
					System.out.println("저자 변경");
					System.out.print(book.get(srcTitle).getAuthor() + "=>");
					String newAuthor = sc.nextLine();								//변경할 저자명 newAuthor변수
					System.out.println();
					System.out.println("출판사 변경");
					System.out.print(book.get(srcTitle).getPublisher() + "=>");
					String newPublisher = sc.nextLine();							//변경할 출판사명 newPublisher변수
					System.out.println();
					System.out.println("가격 변경");
					System.out.print(book.get(srcTitle).getPrice() + "=>");
					int newPrice = sc.nextInt();									//변경할 가격 newPrice변수
					System.out.println();

					BookInfo newBookInfo = new BookInfo(newAuthor, newPublisher, newPrice); //변경할 책의 정보들로 새로운 BookInfo객체 newBookInfo 생성
						book.put(srcTitle, newBookInfo);									//srcTitle의 Value값을 newBookInfo로 덮어쓰기(기존 BookInfo값 삭제)
							System.out.println(srcTitle+"책의 저자는 "+newAuthor+", 출판사는 "+newPublisher+", 가격은 "+newPrice+"원으로 변경되었습니다. \n"); editFlag = false;
				} else if(!(book.containsKey(srcTitle))){System.out.println("등록되어있지 않은 책입니다! \n");}//book이 srcTitle이라는 Key값을 가지고 있지 않을 경우
			} catch (XexistException e) { System.out.println("저자 변경");
				System.out.print(book.get(srcTitle).getAuthor() + "=>");
				String newAuthor = sc.nextLine();
				System.out.println();
				System.out.println("출판사 변경");
				System.out.print(book.get(srcTitle).getPublisher() + "=>");
				String newPublisher = sc.nextLine();						
				System.out.println();
				System.out.println("가격 변경");
				System.out.print(book.get(srcTitle).getPrice() + "=>");
				int newPrice = sc.nextInt();									
				System.out.println();

					BookInfo newBookInfo = new BookInfo(newAuthor, newPublisher, newPrice); 
						book.put(srcTitle, newBookInfo);
							System.out.println(srcTitle+"책의 저자는 "+newAuthor+", 출판사는 "+newPublisher+", 가격은 "+newPrice+"원으로 변경되었습니다. \n"); editFlag = false;
			}
		}
	}

	public static void delete() { //5. 도서정보 삭제
		sc.nextLine();
		System.out.println("도서목록 삭제");
		boolean deleteFlag = true;										//while(deleteFlag)를 반복시키기 위한 boolean값
		while (deleteFlag) {
			System.out.println("삭제하시려는 책의 제목을 입력해주세요. (메뉴로 돌아가시려면 x를 입력해주세요.)");
			String a = sc.nextLine();									//Scanner로 삭제할 책 제목을 받는 변수 a 입력받음
			if (a.equals("x")) { deleteFlag = false; } 					//a가 "x"일 경우 while문 밖으로 탈출=메소드 종료
			else if (book.containsKey(a)) {								//book이 a라는 Key값을 가지고 있을 경우
				book.remove(a);											//book에서 Key값 a와 그 Value값 삭제
				bookList.remove(a);										//bookList에서 엘리먼트 a 삭제(뒤에있는 책들의 번호가 밀리는 것을 막기 위해서)
				System.out.println("책의 정보가 성공적으로 삭제되었습니다.");
			} else {System.out.println(a + "에 대한 정보가 없습니다. 다시 입력해주세요. \n");}
		}
	}
}
