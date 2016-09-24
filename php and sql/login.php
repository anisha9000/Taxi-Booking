<?php 

    #Ensure that the client has provided a value for "FirstNameToSearch" 
    if (isset($_POST["Userid"]) && isset($_POST["Password"]) ){ 
         
        #Setup variables 
        $userid = $_POST["Userid"];
        $password = $_POST["Password"];		
        
        #Connect to Database 
        $con = mysqli_connect("localhost","root","", "travel"); 
         
        #Check connection 
        if (mysqli_connect_errno()) { 
            echo 'Database connection error: ' . mysqli_connect_error(); 
            exit(); 
        } 

        #Escape special characters to avoid SQL injection attacks 
        $userid = mysqli_real_escape_string($con, $userid); 
         
        #Query the database to get the user details. 
        $userdetails = mysqli_query($con, "SELECT * FROM users WHERE id = '$userid' AND password = '$password'"); 

        #If no data was returned, check for any SQL errors 
        if (!$userdetails) { 
            echo 'Could not run query: ' . mysqli_error($con); 
            exit; 
        } 

        #Get the first row of the results 
        $row = mysqli_fetch_row($userdetails); 

        #Build the result array (Assign keys to the values) 
        $result_data = array(  
            'username' => $row[1],  
            'managerid' => $row[3],
            'charge_code' => $row[4],
            'plot' => $row[5],
            'level' => $row[6],
            'address' => $row[7],			
            ); 
			
		//echo $row[2];
		//echo $row[7];

        #Output the JSON data 
        echo json_encode($result_data);  
    }else{ 
        echo "Could not complete query. Missing parameter";  
    } 
?>