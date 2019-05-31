package br.com.samuel.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.samuel.exceptions.ExecuteSqlErrorException;

/**
 * Pattern strategy and Template Method
 * 
 * @author Samuel Oliveira Chaves
 * @param <T>
 */
public abstract class GenericDao<T> {

	private static Log log = LogFactory.getLog(GenericDao.class);

	private void closeConection(Connection con, PreparedStatement pst) {
		try {
			if (pst != null && !pst.isClosed()) {
				pst.close();
			}

			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected T find(StringBuilder sql, Object[] args) {
		ResultSet rs;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			rs = (ResultSet) prepareExecuteQuery(con, pst, sql, args, false);
			T entity = null;
			while (rs.next()) {
				return afterFind(rs, entity);
			}
			return null;
		} catch (SQLException e) {
			log.error("Error find " + getNameEntity() + ":" + sql.toString(), e);
			throw new ExecuteSqlErrorException("There was an error finding the entity:" + getNameEntity());
		} finally {
			closeConection(con, pst);
		}
	}

	protected List<T> findList(StringBuilder sql, Object[] args) {
		ResultSet rs;
		List<T> ret;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			rs = (ResultSet) prepareExecuteQuery(con, pst, sql, args, false);
			ret = new ArrayList<T>();
			while (rs.next()) {
				ret.add(afterFind(rs, getInstance()));
			}
			return ret;
		} catch (SQLException e) {
			log.error("Error find " + getNameEntity() + ":" + sql.toString(), e);
			throw new ExecuteSqlErrorException("There was an error finding the entity:" + getNameEntity());
		} finally {
			closeConection(con, pst);
		}
	}



	protected abstract T afterFind(ResultSet rs, T entity) throws SQLException;
	
	protected abstract T getInstance() ;

	protected final void insert(StringBuilder sql, Object[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			prepareExecuteQuery(con, pst, sql, args, true);
		} catch (SQLException e) {
			log.error("Error insert " + getNameEntity() + ":" + sql.toString(), e);
			throw new ExecuteSqlErrorException("There was an error inserting the entity:" + getNameEntity());
		} finally {
			closeConection(con, pst);
		}
	}

	protected final void update(StringBuilder sql, Object[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			prepareExecuteQuery(con, pst, sql, args, true);
		} catch (SQLException e) {
			log.error("Error update " + getNameEntity() + ":" + sql.toString(), e);
			throw new ExecuteSqlErrorException("There was an error updating the entity:" + getNameEntity());
		} finally {
			closeConection(con, pst);
		}
	}

	protected final void delete(StringBuilder sql, Object[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			prepareExecuteQuery(con, pst, sql, args, true);
		} catch (SQLException e) {
			log.error("Error delete " + getNameEntity() + ":" + sql.toString(), e);
			throw new ExecuteSqlErrorException("There was an error deleting the entity:" + getNameEntity());
		} finally {
			closeConection(con, pst);
		}
	}

	protected final void executeSql(StringBuilder sql) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			prepareExecuteQuery(con, pst, sql, null, true);
		} catch (SQLException e) {
			log.error("Error executeSql:" + sql.toString(), e);
			throw new ExecuteSqlErrorException("There was an error execute Sql the entity:" + getNameEntity());
		} finally {
			closeConection(con, pst);
		}
	}

	protected abstract String getNameEntity();

	// methods private
	private Connection getConnection() {
		return ConnectionFactory.getConection();
	}

	private Object prepareExecuteQuery(Connection con, PreparedStatement preparedStatement, StringBuilder sql,
			Object[] args, boolean isTransation) throws SQLException {
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(StringEscapeUtils.escapeSql(sql.toString()));
			if (args != null && args.length > 0) {
				fillPreparedStatement(preparedStatement, args);
			}
			return isTransation ? preparedStatement.executeUpdate() : preparedStatement.executeQuery();
		} catch (SQLException e) {
			log.error("Erro exceting query:" + sql.toString(), e);
			throw new ExecuteSqlErrorException("Erro exceting query", e);
		}

	}

	private void fillPreparedStatement(PreparedStatement ps, Object... args) throws SQLException {
		int i = 1;
		for (Object arg : args) {
			if (arg instanceof Date) {
				ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
			} else if (arg instanceof Integer) {
				ps.setInt(i++, (Integer) arg);
			} else if (arg instanceof Long) {
				ps.setLong(i++, (Long) arg);
			} else if (arg instanceof Double) {
				ps.setDouble(i++, (Double) arg);
			} else if (arg instanceof Float) {
				ps.setFloat(i++, (Float) arg);
			} else {
				ps.setString(i++, (String) arg);
			}
		}
	}

}
