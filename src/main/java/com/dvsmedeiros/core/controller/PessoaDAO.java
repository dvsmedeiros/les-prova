package com.dvsmedeiros.core.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dvsmedeiros.domain.Pessoa;

public class PessoaDAO extends AbstractDAO<Pessoa> {

	public PessoaDAO() {
		super("PESSOA", "PES_ID");
	}

	protected PessoaDAO(String table, String id) {
		super("PESSOA", "PES_ID");
	}

	protected PessoaDAO(Connection conn, String table, String id) {
		super(conn, "PESSOA", "PES_ID");
	}

	@Override
	public void salvar(Pessoa entidade) {

		PreparedStatement pst = null;

		try {
			openConnection();
			conn.setAutoCommit(false);

			String query = "INSERT INTO PESSOA VALUES(SQ_PES.NEXTVAL, ?, ?)";

			pst = conn.prepareStatement(query, new String[] { "PES_ID" });
			pst.setString(1, entidade.getNome());
			pst.setString(2, entidade.getSobrenome());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();

			int idUsuario = 0;
			if (rs.next()) {
				idUsuario = rs.getInt(1);
			}
			entidade.setId(idUsuario);

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

	@Override
	public void alterar(Pessoa entidade) {

		PreparedStatement pst = null;
		controlTransaction = false;

		try {

			openConnection();
			conn.setAutoCommit(false);

			StringBuilder query = new StringBuilder();
			query.append("UPDATE ");
			query.append("PESSOA ");
			query.append("SET ");
			query.append("PES_NOME = ?, ");
			query.append("PES_SOBRENOME = ? ");
			query.append("WHERE PES_ID = ?");

			pst = conn.prepareStatement(query.toString());

			pst.setString(1, entidade.getNome());
			pst.setString(2, entidade.getSobrenome());
			pst.setLong(3, entidade.getId());

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

	@Override
	public List<Pessoa> cosultar(Pessoa entidade) {
		PreparedStatement pst = null;

		StringBuilder query = new StringBuilder("SELECT * FROM PESSOA WHERE 1=1 ");

		if (entidade != null && entidade.getId() > 0) {
			query.append(" AND PES_ID = ? ");
		}

		if (entidade != null && entidade.getNome() != null && !entidade.getNome().isEmpty()) {
			query.append(" AND PES_NOME = ? ");
		}

		if (entidade != null && entidade.getSobrenome() != null && !entidade.getSobrenome().isEmpty()) {
			query.append(" AND PES_SOBRENOME = ? ");
		}

		try {
			openConnection();
			controlTransaction = false;
			pst = conn.prepareStatement(query.toString());

			int index = 0;

			if (entidade != null && entidade.getId() > 0) {
				pst.setLong(++index, entidade.getId());
			}

			if (entidade != null && entidade.getNome() != null && !entidade.getNome().isEmpty()) {
				pst.setString(++index, entidade.getNome());
			}

			if (entidade != null && entidade.getSobrenome() != null && !entidade.getSobrenome().isEmpty()) {
				pst.setString(++index, entidade.getSobrenome());
			}

			ResultSet rs = pst.executeQuery();
			List<Pessoa> pessoas = new ArrayList<Pessoa>();

			while (rs.next()) {
				Pessoa p = new Pessoa();
				p.setId(rs.getInt("PES_ID"));
				p.setNome(rs.getString("PES_NOME"));
				p.setSobrenome(rs.getString("PES_SOBRENOME"));

				pessoas.add(p);
			}
			rs.close();
			return pessoas;
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

	@Override
	public Pessoa consultar(long id) {
		PreparedStatement pst = null;

		String query = "SELECT * FROM PESSOA WHERE PES_ID = ? ";

		try {
			openConnection();
			pst = conn.prepareStatement(query);

			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Pessoa p = new Pessoa();

			while (rs.next()) {
				p.setId(rs.getInt("PES_ID"));
				p.setNome(rs.getString("PES_NOME"));
				p.setSobrenome(rs.getString("PES_SOBRENOME"));
			}
			rs.close();
			return p;
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

}
