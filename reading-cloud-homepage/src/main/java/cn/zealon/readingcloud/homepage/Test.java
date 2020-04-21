package cn.zealon.readingcloud.homepage;

/**
 * @author: tangyl
 * @since: 2019/8/14
 */
public class Test {


    public static void main(String[] args){
        String[] ids = "10009145,10043456,88043024,60013441,52156116,10053255,1000941,5145666,5551111,555111778,72100766,125626344,323221,44353533,515264,33".split(",");
        for (int i = 0; i < ids.length; i++) {
            String bookId = ids[i];
            String sql = "INSERT INTO `book-ms`.index_booklist_item " +
                    "( booklist_id, book_id, sort_number, creater, create_time, update_time, updater) " +
                    "VALUES( 4, '"+bookId+"', "+(i+2)+", 'admin', '2020-04-09 00:00:00.0', '2020-04-09 00:00:00.0', 'admin');";

            System.out.println(sql);
        }
    }

}
