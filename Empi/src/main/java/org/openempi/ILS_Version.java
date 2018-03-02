package org.openempi;

public class ILS_Version
{
	private final static String version = "ILS 1.08";
	
	public static String getVersion() {
		return version;
	}


//	public static void main(String[] args) throws SQLException {
//		Connection connection = null;
//		try {
//			connection = JdbcHelper.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			if (Objects.isNull(connection) || connection.isClosed()){
//				connection.close();
//			}
//		}
//	}
}
