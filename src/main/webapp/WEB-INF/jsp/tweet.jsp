<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>

    <style>
        form {
            margin-left: 20px;
        }

        fieldset {
            border: none;
            margin-bottom: 20px;
        }

        input[type="submit"] {
            padding: 6px 18px 5px;
            margin-left: 14px;
            background: linear-gradient(lightskyblue, deepskyblue);
            border: 1px solid lightskyblue;
            border-radius: 4px;
            color: #fafafa;
            font-weight: bold;
            font-family: "Book Antiqua";
        }

        input[type="submit"]:hover {
            background: linear-gradient(deepskyblue, lightskyblue);
            cursor: pointer;
        }

        textarea {
            border: 1px solid #bababa;
            border-radius: 4px;
            margin-top: 10px;
        }
        #tweetList {
            margin-top: 15px;
        }
    </style>
</head>
<body>
<h1>Tweet content: <br/> ${tweet}</h1>

<form action="newtweet" method="post">
    <fieldset>
        <label for="tweetMsg">Enter new message</label><br/>
        <textarea id="tweetMsg" name="tweetText" cols="50" rows="5"></textarea>
    </fieldset>
    <input type="submit" value="Create new message"/>
</form>

<form id="tweetList" action="tweetList" method="post">
    <input type="submit" value="Display all tweets"/>
</form>

<p>New Tweet:</p>
<p><c:out value="${newtweet.txt}"/></p>

<p>Tweet list:</p>
<p>
<table>
    <thead>
    <tr>
        <th>№</th><th>Message</th><th></th>
    </tr>
    </thead>

    <c:forEach items="${tweetList}" var="tweet" varStatus="h">
    <tr>
        <td>${h.index + 1}</td>
        <td>${tweet.txt}</td>
        <td><a href="<c:url value="editTweet?id=${tweet.tweetId}"></c:url>"><input type="submit" value="Edit"></a></td>
    </tr>
    </c:forEach>
</table>
</p>
</body>
</html>