package lbw.yht.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import lbw.yht.util.Utils;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class Date2DateTimeTypeHandler implements TypeHandler {

	@Override
	public Object getResult(ResultSet arg0, String arg1) throws SQLException {
		String str = arg0.getString(arg1);
		Date date = Utils.FormatUtil.parseDateTime(str);
		return date;
	}

	@Override
	public Object getResult(CallableStatement arg0, int arg1) throws SQLException {
		Date date = arg0.getDate(arg1);
		return date;
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, Object arg2,
			JdbcType arg3) throws SQLException {
		Date b = (Date) arg2;
		String value = Utils.FormatUtil.formatDatetime(b);
		arg0.setString(arg1, value);
	}

	@Override
	public Object getResult(ResultSet arg0, int arg1) throws SQLException {
		String str = arg0.getString(arg1);
		Date date = Utils.FormatUtil.parseDateTime(str);
		return date;
	}

}
