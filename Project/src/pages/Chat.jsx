import Navbar from '@/components/Navbar';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { chatRooms } from '@/data/mockData';
import ChatRoomCard from '@/components/ChatRoomCard';
import { Link } from 'react-router-dom';
import { ArrowLeft } from 'lucide-react';

const Chat = () => {
  return (
    <div className="min-h-screen bg-gray-50 pb-20">
      <Navbar />
      <main className="container mx-auto pt-20 px-4">
        <div className="flex items-center space-x-4 mb-6">
          <Link to="/">
            <Button variant="ghost" size="icon">
              <ArrowLeft className="h-5 w-5" />
            </Button>
          </Link>
          <h1 className="text-2xl font-bold">Чаты</h1>
        </div>

        <div className="mb-6">
          <Input 
            placeholder="Поиск чата..." 
            className="max-w-lg"
          />
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {chatRooms.map((chatRoom) => (
            <ChatRoomCard key={chatRoom.id} chatRoom={chatRoom} />
          ))}
        </div>
      </main>
    </div>
  );
};

export default Chat;