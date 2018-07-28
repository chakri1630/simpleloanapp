package com.activeweb.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.activeweb.app.beans.LoanApplication;
import com.activeweb.app.rowmappers.LoanApplicationRowMapper;

@Repository
public class ApplicationDAO {

	@Autowired
	DriverManagerDataSource dataSource;

	public int persistApplicationVariation(LoanApplication application) {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("application")
				.usingGeneratedKeyColumns("applicationid");

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(application);

		return (int) simpleJdbcInsert.executeAndReturnKey(namedParameters);
	}

	public void persistApplication(LoanApplication application) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("insert into application values(" + "'," + "'" + application.getLastName() + "'," + "'"
				+ application.getSsn() + "')");
	}

	public Integer retrieveApplicationCount() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM APPLICATION", Integer.class);
	}

	public LoanApplication retrieveApplication(Integer applicationId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		@SuppressWarnings("unchecked")
		LoanApplication application = (LoanApplication) jdbcTemplate.queryForObject(
				"SELECT * FROM APPLICATION where applicationId=?", new Object[] { applicationId },
				new LoanApplicationRowMapper());
		return application;
	}

	public Integer retrieveLoanApplicationPerUser(LoanApplication application) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		String SELECT_BY_SSN = "SELECT COUNT(*) FROM APPLICATION WHERE SSN = :ssn";
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(application);
		Integer count = namedParameterJdbcTemplate.queryForObject(SELECT_BY_SSN, namedParameters, Integer.class);
		return count;
	}

	public boolean deleteApplication(Integer applicationId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update("DELETE FROM APPLICATION WHERE APPLICATIONID=?", applicationId);

		if (count == 1) {
			return true;
		}

		return false;
	}

}
