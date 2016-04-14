<?php ob_start();
echo'<html><body>';

//Store the inputs into variables
$username = $_POST['username'];
$password = $_POST['password'];
$confirm = $_POST['confirm'];
$adminID = $_POST['adminID'];
//Flag to determine if code can run
$ok = true;

//Make sure none of the fields are empty
if (empty($username)) {
    echo 'Username is required<br />';
    $ok = false;
} if (empty($password)) {
    echo 'Password is required<br />';
    $ok = false;
} //End of empty construct

//Make sure password and confirm match
if ($password != $confirm) {
    echo 'Passwords must match<br />';
    $ok = false;
} //End of password confirmation

//Check to see if it's a valid email address format
if (!filter_var($username, FILTER_VALIDATE_EMAIL)){
	echo 'Username is not a valid email address<br />';
	$ok = false;
} //End of email validation

if ($ok) {
	require('db.php');
	//Error handler
	try {
	//If we are editing an existing user, use UPDATE
	if(!empty($adminID)) {
		$sql = "UPDATE admins SET username=:username, password=:password
		WHERE adminID=:adminID";
		
		//Hash the password
		$hashed_password = hash('sha512', $password);
		
		//Fill the params and execute
		$cmd = $conn->prepare($sql);
		$cmd->bindParam(':username', $username, PDO::PARAM_STR, 30);
		$cmd->bindParam(':password', $hashed_password, PDO::PARAM_STR, 128);
		$cmd->bindParam(':adminID', $adminID, PDO::PARAM_INT);
		$cmd->execute();
		
		//Redirect back to the table page
		header('location:users.php');
	} //End of existing user
	
	//If we are creating a new user, use INSERT
	else if(empty($adminID)) {
		//Check for duplicate emails
		$_SESSION['username'] = $_POST['username'];

		//Get any usernames in the database that are the same as the one that tried to register
		$sthandler = $conn->prepare("SELECT username FROM admins WHERE username = :username");
		$sthandler->bindParam(':username', $_SESSION['username']);
		$sthandler->execute();
				
		//If any duplicates are found, throw an error
		if($sthandler->rowCount() > 0){
			echo "Email has already been used.";
		}
		
		//If there are no duplicates, save using INSERT
		else {
			$sql = "INSERT INTO admins (username, password) VALUES (:username, :password)";
			
			//Hash the password
			$hashed_password = hash('sha512', $password);

			//Fill the params and execute
			$cmd = $conn->prepare($sql);
			$cmd->bindParam(':username', $username, PDO::PARAM_STR, 30);
			$cmd->bindParam(':password', $hashed_password, PDO::PARAM_STR, 128);
			$cmd->execute();
			
			//Provide navigation
			echo 'Your registration was successful. <a href="login.php">Click 	here to log in</a>.';
		} //end of else
	} //end of if
	
	} catch (Exception $e) {
		//Email error to myself
		require_once('mail.php');

		//Redirect user to the error page
		header('location:error.php');
	} //End of catch
	$conn=null;
} //End of checking flag

echo'</html></body>';
ob_flush();
?>
