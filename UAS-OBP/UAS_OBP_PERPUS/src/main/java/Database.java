import java.sql.*;

public class Database {
    private Connection db;

    public Database() throws ClassNotFoundException, SQLException {
        //load jdbc driver
        Class.forName("com.mysql.jdbc.Driver");

        //establish connection
        db = DriverManager.getConnection("jdbc:mysql://localhost:3306/perpus", "root", "pwdpwd");
    }

    public boolean isConnected(){
        return (db!=null);
    }

    public void insertEmployee(String id, String fullname, String gender, String position, int salary) throws SQLException{

        String sql = "Insert into employees(id,fullname, gender, position, salary) values( ?,?, ?,?,?)";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1 , id);
        statement.setString(2 , fullname);
        statement.setString(3 , gender);
        statement.setString(4 , position);
        statement.setInt(5 , salary);
        statement.execute();
    }
    public void updateEmployee( String fullname, String gender,String position, int salary,String id )throws SQLException{
        String sql = "UPDATE employees SET fullname = ? , gender = ?, position = ?, salary = ? WHERE id = ?";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, fullname);
        statement.setString(2, gender);
        statement.setString(3, position);
        statement.setInt(4, salary);
        statement.setString(5, id);

        statement.execute();
    }


    public void deleteEmployee(String id) throws SQLException{
        String sql = "DELETE FROM employees WHERE id = ?";
        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, id);
        statement.execute();
    }

    public ResultSet selectEmployees ()throws SQLException{
        String sql  = "SELECT *  FROM employees";

        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);



    }
    public ResultSet selectAdmin(String username, String password) throws SQLException{
        String sql  = "SELECT * FROM admin WHERE username=? AND adminpassword=? ";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        return(rs);

    }
    public ResultSet selectPengguna(String email, String password) throws SQLException{
        String sql  = "SELECT * FROM peminjam WHERE email=? AND peminjampassword=? ";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        return(rs);

    }
    public ResultSet selectBuku ()throws SQLException{
        String sql  = "SELECT isbn,namaBuku,pengarang,tahunTerbit,statusPinjam,fotobuku  FROM  buku ";

        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);

    }
    public ResultSet selectPinjam(String isbn) throws SQLException{
        String sql  = "SELECT * FROM buku WHERE isbn = ?";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, isbn);
        ResultSet rs = statement.executeQuery();
        return(rs);

    }
    public ResultSet selectFoto() throws SQLException{
        String sql = "SELECT * FROM buku WHERE statusPinjam = 'Available'";
        Statement stmt = db.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return(rs);
    }
    public ResultSet selectFoto2(String cari) throws SQLException{
        String sql = "SELECT * FROM buku WHERE statusPinjam = ? AND (namaBuku like  ?  OR penerbit like ?  OR pengarang like   ?)";
        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, "Available");
        statement.setString(2, "%" + cari + "%");
        statement.setString(3, "%" + cari + "%");
        statement.setString(4, "%" + cari + "%");
        ResultSet rs = statement.executeQuery();
        return(rs);
    }

    public void userPinjam(String isbn)throws SQLException{
        String sql = "UPDATE buku set statusPinjam = 'BOOKED' WHERE isbn = ?";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, isbn);

        statement.execute();
    }
    public void tmpInsertlog(String isbn, String nimNidn) throws  SQLException{
        String sql = "INSERT INTO `logpinjam`(`isbn`, `nimNidn`) VALUES(?,?)";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1 , isbn);
        statement.setString(2 , nimNidn);
        statement.execute();

    }
    public void insertAccount(String nimnidn, String nama, String pass, String email, String phone, String faculty) throws SQLException{

        String sql = "INSERT INTO peminjam VALUES (?,?,?,?,?,?)";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1 , nimnidn);
        statement.setString(2 , nama);
        statement.setString(3 , pass);
        statement.setString(4 , email);
        statement.setString(5 , phone);
        statement.setString(6 , faculty);
        statement.execute();
    }
    public ResultSet selectBukuDibookUser(String nimNidn) throws SQLException{
        String sql  = "SELECT * FROM peminjam,logpinjam,buku WHERE logpinjam.nimNIDN = ? AND logpinjam.nimNidn = peminjam.nimNidn AND buku.isbn = logpinjam.isbn AND tanggalPeminjaman is null;";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, nimNidn);
        ResultSet rs = statement.executeQuery();
        return(rs);
    }
    public ResultSet selectBukuDipinjamUser(String nimNidn) throws SQLException{
        String sql  = "SELECT * FROM peminjam,logpinjam,buku WHERE logpinjam.nimNIDN = ? AND logpinjam.nimNidn = peminjam.nimNidn AND buku.isbn = logpinjam.isbn And tanggalPeminjaman is not null AND tanggalPengembalian is null";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, nimNidn);
        ResultSet rs = statement.executeQuery();
        return(rs);

    }

    public ResultSet adminData() throws SQLException {
        String sql = "SELECT *  FROM admin";
        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return (rs);
    }
    public void admininsert (String id, String fullName, String username, String pw) throws SQLException {
        String sql = "INSERT INTO admin(staffId,fullName,username,adminpassword) VALUES (?, ?, ?, ?)";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, id);
        state.setString(2, fullName);
        state.setString(3, username);
        state.setString(4, pw);

        state.execute();
    }
    public void adminupdate(String id, String fullName, String username, String pw) throws SQLException {
        String sql = "UPDATE admin SET fullName = ?, username = ?, adminPassword = ? WHERE StaffId = ?";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, fullName);
        state.setString(2, username);
        state.setString(3, pw);
        state.setString(4, id);

        state.execute();
    }
    public void admindelete(String staffId) throws SQLException {
        String sql = "DELETE FROM admin WHERE staffId = ?";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, staffId);

        state.execute();
    }
    public ResultSet bukuData() throws SQLException{
        String sql  = "SELECT *  FROM buku";

        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }
    public void bukuinsert (String isbn, String namaBuku, String penerbit, String pengarang, int tahunterbit, String statuspinjam, String fotobuku) throws SQLException {
        String sql = "INSERT INTO buku(isbn,namaBuku,penerbit,pengarang, tahunTerbit, statusPinjam, fotobuku) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, isbn);
        state.setString(2, namaBuku);
        state.setString(3, penerbit);
        state.setString(4, pengarang);
        state.setInt(5, tahunterbit);
        state.setString(6, statuspinjam);
        state.setString(7, fotobuku);

        state.execute();
    }
    public void bukuupdate(String isbn, String namaBuku, String penerbit, String pengarang, int tahunterbit, String statuspinjam, String fotobuku) throws SQLException {
        String sql = "UPDATE buku SET namaBuku = ?, penerbit = ?, pengarang = ?, tahunTerbit = ?, statusPinjam = ?, fotobuku = ? WHERE isbn = ?";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, namaBuku);
        state.setString(2, penerbit);
        state.setString(3, pengarang);
        state.setInt(4, tahunterbit);
        state.setString(5, statuspinjam);
        state.setString(6, fotobuku);
        state.setString(7, isbn);

        state.execute();
    }
    public void bukudelete(String isbn) throws SQLException {
        String sql = "DELETE FROM buku WHERE isbn = ?";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, isbn);

        state.execute();
    }
    public ResultSet logPinjamData() throws SQLException{
        String sql  = "SELECT *  FROM logpinjam";
        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }
    public ResultSet requestdata() throws SQLException{
        String sql  = "SELECT *  FROM logpinjam WHERE tanggalPeminjaman is null AND tanggalPengembalian is null";
        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }
    public ResultSet returndata() throws SQLException{
        String sql  = "SELECT *  FROM logpinjam WHERE tanggalPeminjaman is not null AND tanggalPengembalian is null";
        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }
    public void logpinjaminsert ( String isbn, String nimNidn, Date tanggalPeminjaman, Date tanggalKembali) throws SQLException {
        String sql = "INSERT INTO logpinjam(isbn,nimNidn,tanggalPeminjaman, tanggalPengembalian) VALUES ( ?, ?, ?, ?)";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, isbn);
        state.setString(2, nimNidn);
        state.setDate(3, tanggalPeminjaman);
        state.setDate(4, tanggalKembali);

        state.execute();
    }
    public void adminrequest(int orderno,String isbn) throws SQLException {
        String sql = "UPDATE logpinjam SET tanggalPeminjaman = curdate() WHERE orderNo = ?";
        String sql2 = "UPDATE buku SET statusPinjam = 'Dipinjam' WHERE isbn = ?";
        PreparedStatement state = db.prepareStatement(sql);
        state.setInt(1, orderno);
        state.execute();
        PreparedStatement state2 = db.prepareStatement(sql2);
        state2.setString(1, isbn);
        state2.execute();

    }
    public void adminreturn(int orderno,String isbn) throws SQLException {
        String sql = "UPDATE logpinjam SET tanggalPengembalian = curdate() WHERE orderNo = ?";
        String sql2 = "UPDATE buku SET statusPinjam = 'Available' WHERE isbn = ?";
        PreparedStatement state = db.prepareStatement(sql);
        state.setInt(1, orderno);
        state.execute();
        PreparedStatement state2 = db.prepareStatement(sql2);
        state2.setString(1, isbn);
        state2.execute();

    }
    public void logpinjamupdate(int orderNo, String isbn, String nimNidn, Date tanggalPeminjaman, Date tanggalKembali) throws SQLException {
        String sql = "UPDATE logpinjam SET isbn = ?, nimNidn = ?, tanggalPeminjaman = ?, tanggalPengembalian = ? WHERE orderNo = ?";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, isbn);
        state.setString(2, nimNidn);
        state.setDate(3, tanggalPeminjaman);
        state.setDate(4, tanggalKembali);
        state.setInt(5, orderNo);

        state.execute();
    }
    public void logpinjamdelete(int orderNo) throws SQLException {
        String sql = "DELETE FROM logpinjam WHERE orderNo = ?";

        PreparedStatement state = db.prepareStatement(sql);
        state.setInt(1, orderNo);

        state.execute();
    }
    public ResultSet peminjamData() throws SQLException{
        String sql  = "SELECT *  FROM peminjam";

        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }
    public void peminjaminsert (String nimNidn, String namaPeminjam, String peminjampassword, String email, String phone, String faculty) throws SQLException {
        String sql = "INSERT INTO peminjam(nimNidn, namaPeminjam, peminjampassword,email, phone, faculty) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, nimNidn);
        state.setString(2, namaPeminjam);
        state.setString(3, peminjampassword);
        state.setString(4, email);
        state.setString(5, phone);
        state.setString(6, faculty);

        state.execute();
    }
    public void peminjamupdate(String nimNidn, String namaPeminjam, String peminjampassword, String email, String phone, String faculty) throws SQLException {
        String sql = "UPDATE peminjam SET namaPeminjam = ?, peminjampassword = ?, email = ?, phone = ?, faculty = ? WHERE nimNidn = ?";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, namaPeminjam);
        state.setString(2, peminjampassword);
        state.setString(3, email);
        state.setString(4, phone);
        state.setString(5, faculty);
        state.setString(6, nimNidn);

        state.execute();
    }
    public void peminjamdelete(String nimNidn) throws SQLException {
        String sql = "DELETE FROM peminjam WHERE nimNidn = ?";

        PreparedStatement state = db.prepareStatement(sql);
        state.setString(1, nimNidn);

        state.execute();
    }
    /*
    import java.sql.*;

public class Database {
    public Connection conn;

    public Database() throws ClassNotFoundException, SQLException {
        String un = "root";
        String pw = "";
        String url =  "jdbc:mysql://localhost:3306/perpus?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";

        conn = DriverManager.getConnection(url, un, pw);
        isConnected();
    }

    public boolean isConnected(){
        return(conn!=null);
    }

    public void admininsert (String id, String fullName, String username, String pw) throws SQLException {
        String sql = "INSERT INTO admin(staffId,fullName,username,adminpassword) VALUES (?, ?, ?, ?)";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setString(1, id);
        state.setString(2, fullName);
        state.setString(3, username);
        state.setString(4, pw);

        state.execute();
    }
    public void adminupdate(String id, String fullName, String username, String pw) throws SQLException {
        String sql = "UPDATE admin SET fullName = ?, username = ?, pw = ? WHERE id = ?";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setString(1, fullName);
        state.setString(2, username);
        state.setString(3, pw);
        state.setString(4, id);

        state.execute();
    }
    public void admindelete(int staffId) throws SQLException {
        String sql = "DELETE FROM admin WHERE staffId = ?";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setInt(1, staffId);

        state.execute();
    }
    public void bukuinsert (String isbn, String namaBuku, String penerbit, String pengarang, int tahunterbit, String statuspinjam, String fotobuku) throws SQLException {
        String sql = "INSERT INTO buku(isbn,namaBuku,penerbit,pengarang, tahunTerbit, statusPinjam, fotobuku) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setString(1, isbn);
        state.setString(2, namaBuku);
        state.setString(3, penerbit);
        state.setString(4, pengarang);
        state.setInt(5, tahunterbit);
        state.setString(6, statuspinjam);
        state.setString(7, fotobuku);

        state.execute();
    }
    public void bukuupdate(String isbn, String namaBuku, String penerbit, String pengarang, int tahunterbit, String statuspinjam, String fotobuku) throws SQLException {
        String sql = "UPDATE buku SET namaBuku = ?, penerbit = ?, pengarang = ?, tahunterbit = ?, statuspinjam = ?, fotobuku = ? WHERE isbn = ?";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setString(1, namaBuku);
        state.setString(2, penerbit);
        state.setString(3, pengarang);
        state.setInt(4, tahunterbit);
        state.setString(5, statuspinjam);
        state.setString(6, fotobuku);
        state.setString(7, isbn);

        state.execute();
    }
    public void bukudelete(int isbn) throws SQLException {
        String sql = "DELETE FROM buku WHERE isbn = ?";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setInt(1, isbn);

        state.execute();
    }

    public void logpinjaminsert (int orderNo, String isbn, String nimNidn, Date tanggalPeminjaman, Date tanggalKembali) throws SQLException {
        String sql = "INSERT INTO logpinjam(orderNo,isbn,nimNidn,tanggalPeminjaman, tanggalPengembalian) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setInt(1, orderNo);
        state.setString(2, isbn);
        state.setString(3, nimNidn);
        state.setDate(4, tanggalPeminjaman);
        state.setDate(5, tanggalKembali);

        state.execute();
    }
    public void logpinjamupdate(int orderNo, String isbn, String nimNidn, Date tanggalPeminjaman, Date tanggalKembali) throws SQLException {
        String sql = "UPDATE logpinjam SET isbn = ?, nimNidn = ?, tanggalPeminjaman = ?, tanggalKembali = ? WHERE orderNo = ?";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setString(1, isbn);
        state.setString(2, nimNidn);
        state.setDate(3, tanggalPeminjaman);
        state.setDate(4, tanggalKembali);
        state.setInt(5, orderNo);

        state.execute();
    }
    public void logpinjamdelete(int orderNo) throws SQLException {
        String sql = "DELETE FROM logpinjam WHERE orderNo = ?";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setInt(1, orderNo);

        state.execute();
    }

    public void peminjaminsert (String nimNidn, String namaPeminjam, String peminjampassword, String email, String phone, String faculty) throws SQLException {
        String sql = "INSERT INTO peminjam(nimNidn, namaPeminjam, peminjampassword,email, phone, faculty) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setString(1, nimNidn);
        state.setString(2, namaPeminjam);
        state.setString(3, peminjampassword);
        state.setString(4, email);
        state.setString(5, phone);
        state.setString(6, faculty);

        state.execute();
    }
    public void peminjamupdate(String nimNidn, String namaPeminjam, String peminjampassword, String email, String phone, String faculty) throws SQLException {
        String sql = "UPDATE peminjam SET namaPeminjam = ?, peminjampassword = ?, email = ?, phone = ?, faculty = ? WHERE nimNidn = ?";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setString(1, namaPeminjam);
        state.setString(2, peminjampassword);
        state.setString(3, email);
        state.setString(4, phone);
        state.setString(5, faculty);
        state.setString(6, nimNidn);

        state.execute();
    }
    public void peminjamdelete(int nimNidn) throws SQLException {
        String sql = "DELETE FROM peminjam WHERE nimNidn = ?";

        PreparedStatement state = conn.prepareStatement(sql);
        state.setInt(1, nimNidn);

        state.execute();
    }
    public ResultSet adminData() throws SQLException{
        String sql  = "SELECT *  FROM admin";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }
    public ResultSet bukuData() throws SQLException{
        String sql  = "SELECT *  FROM buku";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }
    public ResultSet logPinjamData() throws SQLException{
        String sql  = "SELECT *  FROM logpinjam";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }
    public ResultSet peminjamData() throws SQLException{
        String sql  = "SELECT *  FROM peminjam";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return(rs);
    }



    /*User*/
}




