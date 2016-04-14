<?php ob_start();  //Start the output buffer

//Read the adminID
$adminID = $_GET['adminID'];

//Check to see if it's numeric
if (is_numeric($adminID)) {
	try {
		require('db.php');

		//Write and run the delete query
		$sql = "DELETE FROM admins WHERE adminID = :adminID";

		$cmd = $conn->prepare($sql);
		$cmd->bindParam(':adminID', $adminID, PDO::PARAM_INT);
		$cmd->execute();
	} catch (Exception $e) {
		//Email error to myself
		require_once('mail.php');

		//Redirect user to the error page
		header('location:error.php');
	} //End of catch

    $conn = null;

    //Redirect
    header('location:users.php');
}

//Clear the output buffer
ob_flush();
?>