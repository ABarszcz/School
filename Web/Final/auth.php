<?php
//Access the current session
session_start();
//Checks to see if the user is logged in by looking for an active user ID. If they are not, it will redirect them to the login page.
if (empty($_SESSION['adminID'])) {
    header('location:login.php');
    exit();
}

?>
