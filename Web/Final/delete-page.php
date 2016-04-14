<?php ob_start();  //Start the output buffer

//Read the pageID
$pageID = $_GET['pageID'];

//Check to see if it's numeric
if (is_numeric($pageID)) {
	try {
		require('db.php');

		//Write and run the delete query
		$sql = "DELETE FROM pages WHERE pageID = :pageID";

		$cmd = $conn->prepare($sql);
		$cmd->bindParam(':pageID', $pageID, PDO::PARAM_INT);
		$cmd->execute();
	} catch (Exception $e) {
		//Email error to myself
		require_once('mail.php');

		//Redirect user to the error page
		header('location:error.php');
	} //End of catch

    $conn = null;

    //Redirect
    header('location:pages.php');
}

//Clear the output buffer
ob_flush();
?>