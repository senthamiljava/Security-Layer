package com.revature.security.boot.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;

public class DataUtils {

  private DataUtils() {}

  /**
   * Convenience method for generating parameter lists for our DataRetriever API.
   * 
   * @param params One or more {@link QueryParameter}
   * @return List of {@link QueryParameter}
   */
  public static List<QueryParameter<?>> constructParamList(QueryParameter<?>... params) {
    List<QueryParameter<?>> list = new ArrayList<>(params.length);
    for (QueryParameter<?> param : params) {
      list.add(param);
    }
    return list;
  }

  /**
   * Assister method to DataRetriever and DataModifier implementations. This method handles adding
   * the values of many {@link QueryParameter}s to the Hibernate {@link Query}.
   * 
   * @param query
   * @param parameter
   */
  public static void setQueryParameters(Query query, Collection<QueryParameter<?>> parameters) {
    for (QueryParameter<?> parameter : parameters) {
      String name = parameter.getName();
      Object value = parameter.getValue();
      if (value instanceof Collection) {
        query.setParameterList(name, (Collection<?>) value);
      } else {
        query.setParameter(name, value);
      }
    }
  }

}
