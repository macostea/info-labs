   try {
       socket = new Socket("localhost", 4322);
       OutputStream outputStream = socket.getOutputStream();
       InputStream inputStream = socket.getInputStream();

       ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
       objectOutputStream.flush();

       ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

       Packet packet = new Packet();
       packet.setRequestType(RequestType.GET_ALL_ORDERS);
       objectOutputStream.writeObject(packet);

       Packet p = (Packet)objectInputStream.readObject();

       ArrayList<Order> orders = p.getOrders();
       for (Order o : orders) {
           System.out.println(o.getStatus());
       }
       System.out.println(p.getOrders());

   } catch (IOException ex) {
       Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
   } catch (ClassNotFoundException ex) {
       Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
   }