<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Graph Search Algorithms</title>
<link rel="stylesheet" type="text/css" href="/css/home.css">

</head>
<body>

	<div class="main">
		<h1>ITG - Graph Search Algorithms task</h1>
		<table class="contain-data">
			<tr>
				<td>
					<form action="UploadCSV" method="POST" enctype="multipart/form-data">
						<table class="input-data">
							<c:choose>
						         <c:when test = "${ records eq null}">
							         <tr>
							         	<td>
							         		<input type="file" name="file" size="50" /> 
							         	</td>
							         </tr>
							         <tr>
							         	<td>
							         		<input type="submit" value="Upload File" />
							         	</td>
							         </tr>
						         </c:when>
						         <c:otherwise>
						         	<tr>
							         	<td class="success">
							         		File uploaded Successfully.
							         	</td>
							         </tr>
							         <tr>
							         	<td>
							         		<a href="deleteFile">Upload new file.</a>
							         	</td>
							         </tr>         
						         </c:otherwise>
						    </c:choose>
						</table>
					</form>
				</td>
				
				<td>
					<form class="input-data" action="setPath" method="POST">
						<table>
							<tr>
								<td>
									<label>Start
									<input id="start" type="number" name="start" value="start" placeholder="${start}" ${ records eq null ?  'disabled="disabled"'  : '' }/>
									</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>Goal
									<input id="goal" type="number" name="goal" value="goal" placeholder="${goal}" ${ records eq null ?  'disabled="disabled"'  : '' }/>
									</label> 
								</td>
							</tr>
							<tr>
								<td>
									<input class="push-btn" type="submit" ${ records eq null ?  'disabled="disabled" value="Set Path - Upload file please"'  : 'value="Set Path"' } />
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>

		<br>
		
		<table class="result-data">
			<tr>
				<th>Algorithm</th>
				<th># of Evaluated Nodes</th>
				<th>Evaluated Nodes</th>
				<th>Path</th>
				<th>Distance</th>
				<th>Action</th>
			</tr>
			<tr>
				<td>UCS</td>
				<td><c:out value="${UCSdata.evaluated}" /></td>
				<td><c:out value="${UCSdata.visited}" /></td>
				<td><c:out value="${UCSdata.path}" /></td>
				<td><c:out value="${UCSdata.minCost}" /></td>
				<td>
					<form action="ucs" method="POST"
						enctype="multipart/form-data">
						<input class="push-btn" type="submit"  ${ start eq null ?  'disabled="disabled" value="Set Path"'  : 'value="Search"' }/>
					</form>
				</td>
			</tr>
			<tr class="even-tr">
				<td>GBS</td>
				<td><c:out value="${GBSdata.evaluated}" /></td>
				<td><c:out value="${GBSdata.visited}" /></td>
				<td><c:out value="${GBSdata.path}" /></td>
				<td><c:out value="${GBSdata.minCost}" /></td>
				<td>
					<form action="greedy" method="POST"
						enctype="multipart/form-data">
						<input class="push-btn" type="submit"  ${ start eq null ?  'disabled="disabled" value="Set Path"'  : 'value="Search"' }/>
					</form>
				</td>
			</tr>
			
			<tr>
				<td>A*</td>
				<td><c:out value="${ASdata.evaluated}" /></td>
				<td><c:out value="${ASdata.visited}" /></td>
				<td><c:out value="${ASdata.path}" /></td>
				<td><c:out value="${ASdata.minCost}" /></td>
				<td>
					<form action="AStar" method="POST"
						enctype="multipart/form-data">
						<input class="push-btn" type="submit"  ${ start eq null ?  'disabled="disabled" value="Set Path"'  : 'value="Search"' }/>
					</form>
				</td>
			</tr>
		</table>
		<form class="right-align" action="searchAll" method="POST">
			<input class="push-btn" type="submit"  ${ start eq null ?  'disabled="disabled" value="Search All - Set Path"'  : 'value="Search All"' }/>
		</form>
	</div>
</body>
</html>