import java.net._
import java.io._

val ss = new ServerSocket(4000)
val sock = ss.accept
val is = sock.getInputStream
val os = sock.getOutputStream
Thread.sleep(100)
val buf = new Array[Byte](is.available)
is.read(buf)
println(new String(buf))
val message = """HTTP/1.1 200 OK

Hello World!
"""
os.write(message.getBytes)
sock.close

/*
GET / HTTP/1.1
Host: www.google.com:80


*/
