import { useParams, Link } from "react-router-dom";
import Navbar from "@/components/Navbar";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { ArrowLeft, Send } from "lucide-react";
import { chatMessages, chatRooms } from "@/data/mockData";
import { useEffect, useRef, useState } from "react";
import ChatMessage from "@/components/ChatMessage";
import { Switch } from "@/components/ui/switch";
import { Label } from "@/components/ui/label";
import { v4 as uuidv4 } from 'uuid';
import { currentUser } from "@/data/mockData";
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from "@/components/ui/tooltip";

const ChatRoom = () => {
  const { roomId } = useParams();
  const [messages, setMessages] = useState(
    chatMessages[roomId || "chat1"] || []
  );
  const [newMessage, setNewMessage] = useState("");
  const [isAnonymous, setIsAnonymous] = useState(false);
  const messageEndRef = useRef(null);

  const room = chatRooms.find((r) => r.id === roomId);

  useEffect(() => {
    messageEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const handleSendMessage = (e) => {
    e.preventDefault();
    if (!newMessage.trim()) return;

    const message = {
      id: uuidv4(),
      text: newMessage,
      author: {
        id: currentUser.id,
        name: currentUser.name,
        avatar: currentUser.avatar
      },
      isAnonymous,
      likes: 0,
      createdAt: new Date().toISOString()
    };

    setMessages([...messages, message]);
    setNewMessage("");
  };

  if (!room) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <h1 className="text-2xl font-bold mb-4">Чат не найден</h1>
          <Link to="/chat">
            <Button>Вернуться к списку чатов</Button>
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <main className="container mx-auto pt-20 px-4 pb-20">
        <div className="flex items-center space-x-4 mb-6">
          <Link to="/chat">
            <Button variant="ghost" size="icon">
              <ArrowLeft className="h-5 w-5" />
            </Button>
          </Link>
          <div className="text-2xl mr-2">{room.icon}</div>
          <div>
            <h1 className="text-2xl font-bold">{room.name}</h1>
            <p className="text-sm text-gray-500">{room.description}</p>
          </div>
        </div>

        <div className="bg-white rounded-lg shadow-sm p-4 mb-4">
          <div className="mb-4 max-h-[calc(100vh-320px)] overflow-y-auto">
            {messages.map((message) => (
              <ChatMessage key={message.id} message={message} />
            ))}
            <div ref={messageEndRef} />
          </div>

          <form onSubmit={handleSendMessage} className="flex flex-col space-y-2">
            <div className="flex items-center justify-end space-x-2">
              <TooltipProvider>
                <Tooltip>
                  <TooltipTrigger asChild>
                    <div className="flex items-center space-x-2">
                      <Switch
                        id="anonymous-mode"
                        checked={isAnonymous}
                        onCheckedChange={setIsAnonymous}
                      />
                      <Label htmlFor="anonymous-mode">Анонимно</Label>
                    </div>
                  </TooltipTrigger>
                  <TooltipContent>
                    <p>В анонимном режиме ваше имя не будет видно в чате</p>
                  </TooltipContent>
                </Tooltip>
              </TooltipProvider>
            </div>
            <div className="flex space-x-2">
              <Input
                placeholder="Написать сообщение..."
                value={newMessage}
                onChange={(e) => setNewMessage(e.target.value)}
              />
              <Button type="submit">
                <Send className="h-4 w-4" />
              </Button>
            </div>
          </form>
        </div>
      </main>
    </div>
  );
};

export default ChatRoom;