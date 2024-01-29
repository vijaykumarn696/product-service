package vijay.dev.productservice.thirdpartyclient;

public class MySQLDBStore {
    public static String Categories_By_UUIDS="select * from Category where category_id in(:uuids:)";
}
