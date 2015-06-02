<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" type="text/css" href="style.css">
   <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   <script src="script.js"></script>
<title>Spielbrett</title>


</head>
<body>
<center>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='#'>Speichern als</a></li>
   <li><a href='#'>SER</a></li>
   <li><a href='#'>CSV</a></li>
   <li><a href='#'>PDF</a></li>
   <li><a href='#'>XML</a></li>
</ul>
</div>
</center>

<div id="dice">
<img src="Bilder/dice6.png">
</div>

<div id="steuerung">
<font color="white" face="Trebuchet MS">Steuerung</font>
<br><br>
<input id="refresh" type = "submit" value = "Refresh">
<br><br>
<input id="wurf" type = "submit" value = "Würfeln">
<br><br>
<input id="laufKi" type = "submit" value = "Laufen (Ki)">
<br><br>
<input id="beenden" type = "submit" value = "Zug Beenden">
</div>
<br>

<div id="brett">
 <form action="Spielbrett" method="post">

<center><table width="20">
<tr>  <th>S2</th><th>S1</th> 	<th></th><th></th> 		<th>9</th><th>10</th><th>11</th> 	<th></th><th></th> 		<th>S2</th><th>S1</th>  </tr>
<tr>  <th>S3</th><th>S4</th> 	<th></th><th></th> 		<th>8</th><th>E1</th><th>12</th> 	<th></th><th></th> 		<th>S3</th><th>S4</th>  </tr>
<tr>  <th></th><th></th> 		<th></th><th></th> 		<th>7</th><th>E2</th><th>13</th> 	<th></th><th></th> 		<th></th><th></th>  	</tr>
<tr>  <th></th><th></th> 		<th></th><th></th> 		<th>6</th><th>E3</th><th>14</th> 	<th></th><th></th> 		<th></th><th></th>  	</tr>
<tr>  <th>1</th><th>2</th> 		<th>3</th><th>4</th> 	<th>5</th><th>E4</th><th>15</th> 	<th>16</th><th>17</th> 	<th>18</th><th>19</th>  </tr>
<tr>  <th>40</th><th>E1</th> 	<th>E2</th><th>E3</th> 	<th>E4</th><th></th><th>E4</th> 	<th>E3</th><th>E2</th> 	<th>E1</th><th>20</th>  </tr>  
<tr>  <th>39</th><th>38</th> 	<th>37</th><th>36</th> 	<th>35</th><th>E4</th><th>25</th> 	<th>24</th><th>23</th> 	<th>22</th><th>21</th>  </tr> 
<tr>  <th></th><th></th> 		<th></th><th></th> 		<th>34</th><th>E3</th><th>26</th> 	<th></th><th></th> 		<th></th><th></th>  	</tr>
<tr>  <th></th><th></th> 		<th></th><th></th> 		<th>33</th><th>E2</th><th>27</th> 	<th></th><th></th> 		<th></th><th></th>  	</tr>
<tr>  <th>S2</th><th>S1</th> 	<th></th><th></th> 		<th>32</th><th>E1</th><th>28</th> 	<th></th><th></th> 		<th>S2</th><th>S1</th>  </tr>
<tr>  <th>S3</th><th>S4</th> 	<th></th><th></th> 		<th>31</th><th>30</th><th>29</th> 	<th></th><th></th> 		<th>S3</th><th>S4</th>  </tr>
</table></center>

</form>
</div>
<br>
<center><div id="log"><textarea readonly disabled id="logging" rows="1" cols="1"></textarea></div></center>


</body>
</html>
