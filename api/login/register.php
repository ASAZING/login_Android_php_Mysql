<?php 
require_once 'connection.php';
$name=$_POST['name'];
$lastname=$_POST['lastname'];
$email=$_POST['email'];
$pass=$_POST['pass'];


 try {
     if(connect()){
    $query =$conn->prepare("INSERT INTO users (name_user, lastname_user, email, pass) VALUES (?, ?, ?, ?)");
    $query->bind_param('ssss',$name, $lastname,$email,$pass);
    if($query->execute()){
    	 echo "Datos ingresados correctamente";
    
    }else {
        die("Error al insertar datos");
    }
    $query->close();
    $conn->close();
     }else {
         echo "No se puede conectar";
     }
    
 } catch (\Throwable $th) {
     echo "error".$th;
 }

?>
