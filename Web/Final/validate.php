<?php ob_start();
//Start the body because the header does not need to be called
echo'<html><body>';

$username = $_POST['username'];
$password = hash('sha512', $_POST['password']);

//Error handler
try {
	require('db.php');
	//Create sql command
	$sql = "SELECT adminID FROM admins WHERE username = :username AND password = :password";

	//Prepare and execute sql command
	$cmd = $conn->prepare($sql);
	$cmd->bindParam(':username', $username, PDO::PARAM_STR, 50);
	$cmd->bindParam(':password', $password, PDO::PARAM_STR, 128);
	$cmd->execute();

	//Retrieve information from executed sql command
	$adminID = $cmd->fetchColumn();
	$count = $cmd->rowCount();

	//If the username and password do not exist, display error message and exit.
	if ($count < 1) {
		echo 'Invalid Login';
		$conn=null;
		exit();
	} //End of validation
	
	//If the username and password do exist, log the user in (by saving their ID in the session)
	else {
		session_start(); //Access the session
		$_SESSION['adminID'] = $adminID; //Save ID to session
	} //End of else
} catch (Exception $e) {
    //Email error to myself
    require_once('mail.php');

    //Redirect user to the error page
    header('location:error.php');
} //End of try/catch

$conn = null;

header('location: users.php');

//Close the body and html tags
echo'</body></html>';
ob_flush(); ?>

