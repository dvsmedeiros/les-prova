package com.dvsmedeiros.core.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dvsmedeiros.core.utils.Conexao;
import com.dvsmedeiros.domain.EntidadeDominio;

public abstract class AbstractDAO<E extends EntidadeDominio> implements IDAO<E> {

	protected Connection conn;
	protected String table;
	protected String id;
	protected boolean controlTransaction;
	
	public AbstractDAO(Connection conn, String table, String id) {
		this.table = table;
		this.id = id;
		this.conn = conn;
	}

	protected AbstractDAO(String table, String id) {
		this.table = table;
		this.id = id;
	}
	
	@Override
	public void excluir(E entiadade) {
		
		openConnection();
		PreparedStatement pst = null;
		
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(table);
		query.append(" WHERE ");
		query.append(id);
		query.append(" = ");
		query.append(" ? ");
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(query.toString());
			pst.setLong(1, entiadade.getId());
			
			pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1.getMessage());
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			
			try {
				pst.close();
				if (controlTransaction)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	protected void openConnection() {
		try {

			if (conn == null || conn.isClosed())
				conn = Conexao.getConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
