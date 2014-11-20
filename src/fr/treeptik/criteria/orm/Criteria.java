package fr.treeptik.criteria.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.treeptik.criteria.utils.ConnectionUtils;

public class Criteria<T> {

	private Class<T> type;

	private List<Condition> conditions = new ArrayList<Condition>();

	public Criteria(Class<T> type) {
		this.type = type;
	}

	public Criteria<T> add(Condition condition) {
		conditions.add(condition);

		return this;
	}

	public List<T> list() {

		String query = "Select * From " + type.getSimpleName();

		List<T> result = null;
		try {

			if ( ! conditions.isEmpty()) {

				StringBuilder queryBuilder = new StringBuilder(query
						+ " WHERE ");

				for (Condition condition : conditions) {
					queryBuilder.append(condition.getPropertyName() + " "
							+ condition.getOperator() + " ? AND ");
				}

				query = queryBuilder.toString();
				query = query.substring(0, query.lastIndexOf("AND"));

				System.out.println(query);

			}

			Connection connection = ConnectionUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			if ( ! conditions.isEmpty()) {

				int i = 1;
				
				for (Condition condition : conditions) {
					System.out.println(conditions.size());
					statement.setObject(i, condition.getValue());
					//statement.setObject(i+1, condition.getValue());
					i++;
				}
			}

			ResultSet resultSet = statement.executeQuery();

			result = createListPojo(resultSet);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	private T createPojo(ResultSet resultSet) {

		Field[] fields = type.getDeclaredFields();
		T instance = null;
		try {
			instance = type.newInstance();
			for (Field field : fields) {

				if (field.getName().equalsIgnoreCase("serialVersionUID")) {
					continue;
				}

				field.setAccessible(true);
				field.set(instance, resultSet.getObject(field.getName()));

			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}

		return instance;

	}

	private List<T> createListPojo(ResultSet resultSet) {

		List<T> result = new ArrayList<T>();
		try {
			while (resultSet.next()) {
				result.add(createPojo(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

}
