<?php
//Connect to the database and turn on error messages.
$conn = new PDO('mysql:host=sql.computerstudi.es;dbname=', '', '');
$conn -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
?>