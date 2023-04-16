package xe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.Database;

public class DanhSachXe {
	ArrayList<Xe> dsXe;
	Xe xe;
	public DanhSachXe() {
		dsXe=new ArrayList<Xe>();
		xe=new Xe();
	}
	public ArrayList<Xe> docTubang(){
		try {
			Database.getinstance();
			Connection con = Database.getinstance().getConnection();
			String sql="select maxe,tenxe,loaixe,somay,sokhung,mauxe,nuocsx,soluong,giaxe,nhacungcap from xe";
			Statement statement=con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				String ma=rs.getString(1);
				String ten=rs.getString(2);
				String loai=rs.getString(3);
				int somay=rs.getInt(4);
				int sokhung=rs.getInt(5);
				String mau=rs.getString(6);
				String nuoc=rs.getString(7);
				int soluong=rs.getInt(8);
				Double gia=rs.getDouble(9);
				String nhacungcap=rs.getString(10);
				Xe s=new Xe(ma, ten, loai, somay, sokhung, mau, nuoc, soluong, gia, nhacungcap);
				dsXe.add(s);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsXe;
	}
	public boolean create(String mxe,String txe,String lxe,int smay,int skhung,String mau,String nsx,int sluong,double gxe,String ncc) {
		Database.getinstance();
		Connection con = Database.getinstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into Xe values(?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, mxe);
			stmt.setString(2, txe);
			stmt.setString(3, lxe);
			stmt.setInt(4, smay);
			stmt.setInt(5, skhung);
			stmt.setString(6, mau);
			stmt.setString(7, nsx);
			stmt.setInt(8, sluong);
			stmt.setDouble(9, gxe);
			stmt.setString(10, ncc);
			n=stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean them(Xe xe) {
		if(dsXe.contains(xe))
			return false;
			dsXe.add(xe);
		return true;
	}
	public boolean update(String mxe,String txe,String lxe,int smay,int skhung,String mau,String nsx,int sluong,double gxe,String ncc) {
		Database.getinstance();
		Connection con = Database.getinstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update Xe "+"set tenXe=?,"+"loaiXe=?,"+"soMay=?,"+"soKhung=?,"+"mauXe=?,"+"nuocSX=?,"+"soLuong=?,"+"giaXe=?,"+"nhaCungCap=? "+"where maXe=?");
			stmt.setString(1, txe);
			stmt.setString(2, lxe);
			stmt.setInt(3, smay);
			stmt.setInt(4, skhung);
			stmt.setString(5, mau);
			stmt.setString(6, nsx);
			stmt.setInt(7, sluong);
			stmt.setDouble(8, gxe);
			stmt.setString(9, ncc);
			stmt.setString(10, mxe);
			n=stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean delete(String mxe) {
		Database.getinstance();
		Connection con = Database.getinstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("delete from Xe where maXe=?");
			stmt.setString(1, mxe);
			n=stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public int timKiem(String mxe) {
		int i=0;
		i++;
		if(dsXe.equals(mxe))
			return i;
		return -1;
	}
	public ArrayList<Xe> getDsXe(){
		return dsXe;
	}
}
