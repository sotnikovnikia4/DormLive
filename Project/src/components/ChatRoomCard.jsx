import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { ArrowRight, Users } from 'lucide-react';
import { Link } from 'react-router-dom';

const ChatRoomCard = ({ chatRoom }) => {
  return (
    <Card className="overflow-hidden transition-all duration-200 hover:shadow-md">
      <CardHeader className="pb-2">
        <div className="flex justify-between items-start">
          <div className="flex items-center space-x-2">
            <div className="text-2xl">{chatRoom.icon}</div>
            <CardTitle className="text-lg">{chatRoom.name}</CardTitle>
          </div>
          <div className="flex items-center text-xs text-gray-500">
            <Users className="h-3 w-3 mr-1" />
            <span>{chatRoom.participants}</span>
          </div>
        </div>
        <CardDescription>{chatRoom.description}</CardDescription>
      </CardHeader>
      <CardContent>
        <div className="flex justify-end">
          <Link to={`/chat/${chatRoom.id}`}>
            <Button size="sm" variant="secondary">
              Присоединиться
              <ArrowRight className="ml-1 h-3 w-3" />
            </Button>
          </Link>
        </div>
      </CardContent>
    </Card>
  );
};

export default ChatRoomCard;