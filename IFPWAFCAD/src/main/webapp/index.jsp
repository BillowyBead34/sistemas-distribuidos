<!DOCTYPE html>
<html>
  <head>
    <title>App title</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="style.css" />
  </head>
  <body>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

    <sql:query var="subjects" dataSource="jdbc/IFPWAFCAD">
      SELECT id, name FROM Subject
    </sql:query>

    <table border="1">
      <!-- column headers -->
      <tr>
        <c:forEach var="columnName" items="${subjects.columnNames}">
          <th><c:out value="${columnName}"/></th>
          </c:forEach>
      </tr>
      <!-- column data -->
      <c:forEach var="row" items="${subjects.rowsByIndex}">
        <tr>
          <c:forEach var="column" items="${row}">
            <td><c:out value="${column}"/></td>
          </c:forEach>
        </tr>
      </c:forEach>
    </table>

    <h2>
      Welcome to <strong>IFPWAFCAD</strong>, the International Former
      Professional Wrestlers' Association for Counseling and Development!
    </h2>

    <table border="0">
      <thead>
        <tr>
          <th>IFPWAFCAD offers expert counseling in a wide range of fields.</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            To view the contact details of an IFPWAFCAD certified former
            professional wrestler in your area, select a subject below:
          </td>
        </tr>
        <tr>
          <td>
            <form action="response.jsp">
              <strong>Select a subject:</strong>
              <select name="subject_id">
                <c:forEach var="row" items="${subjects.rows}">
                  <option value="${row.id}">${row.name}</option>
                </c:forEach>
              </select>
              <input type="submit" value="submit" name="submit" />
            </form>
          </td>
        </tr>
      </tbody>
    </table>
  </body>
</html>
