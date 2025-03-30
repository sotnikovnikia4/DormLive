import { Link } from 'react-router-dom';
import { Bell, User, MapPin, MessageSquare, Home } from 'lucide-react';
import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import { useToast } from '@/components/ui/use-toast';
import { useState } from 'react';
import { emergencyNotifications } from '@/data/mockData';

const Navbar = () => {
  const { toast } = useToast();
  const [unreadCount, setUnreadCount] = useState(
    emergencyNotifications.filter((n) => !n.isRead).length
  );

  const handleNotificationClick = () => {
    if (unreadCount > 0) {
      toast({
        title: emergencyNotifications[0].title,
        description: emergencyNotifications[0].description,
        variant: 'destructive',
      });
      setUnreadCount(0);
    } else {
      toast({
        title: 'Нет новых уведомлений',
        description: 'Все уведомления прочитаны',
      });
    }
  };

  return (
    <nav className="border-b fixed top-0 left-0 right-0 bg-white z-10">
      <div className="container mx-auto px-4 py-2 flex items-center justify-between">
        <Link to="/" className="flex items-center space-x-2">
          <div className="bg-primary rounded-full p-2 flex items-center justify-center">
            <MapPin className="h-4 w-4 text-white" />
          </div>
          <span className="font-bold text-xl">Общага Live</span>
        </Link>

        <div className="flex items-center space-x-1">
          <Link to="/">
            <Button variant="ghost" size="icon">
              <Home className="h-5 w-5" />
            </Button>
          </Link>
          <Link to="/chat">
            <Button variant="ghost" size="icon">
              <MessageSquare className="h-5 w-5" />
            </Button>
          </Link>
          <Button
            variant="ghost"
            size="icon"
            onClick={handleNotificationClick}
            className="relative"
          >
            <Bell className="h-5 w-5" />
            {unreadCount > 0 && (
              <Badge
                className="absolute -top-1 -right-1 px-1.5 py-0.5 min-w-[1.25rem] min-h-[1.25rem] flex items-center justify-center bg-red-500 text-white text-xs"
                variant="secondary"
              >
                {unreadCount}
              </Badge>
            )}
          </Button>
          <Link to="/profile">
            <Button variant="ghost" size="icon">
              <User className="h-5 w-5" />
            </Button>
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;