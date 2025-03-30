import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import { MessageSquare, Tag, CalendarDays, MapPin } from 'lucide-react';
import { useState } from 'react';
import { formatDistanceToNow } from 'date-fns';
import { ru } from 'date-fns/locale';
import { useToast } from '@/components/ui/use-toast';

const getTypeColor = (type) => {
  switch (type) {
    case 'sell':
      return 'bg-blue-100 text-blue-800';
    case 'search':
      return 'bg-green-100 text-green-800';
    case 'event':
      return 'bg-purple-100 text-purple-800';
    default:
      return 'bg-gray-100 text-gray-800';
  }
};

const getTypeText = (type) => {
  switch (type) {
    case 'sell':
      return 'Продажа';
    case 'search':
      return 'Поиск';
    case 'event':
      return 'Событие';
    default:
      return type;
  }
};

const AnnouncementCard = ({ announcement }) => {
  const [active, setActive] = useState(announcement.isActive);
  const { toast } = useToast();

  const handleMessage = () => {
    toast({
      title: `Сообщение отправлено ${announcement.author.name}`,
      description: 'Ожидайте ответа в течение дня',
    });
  };

  const toggleActive = () => {
    setActive(!active);
    toast({
      title: active ? 'Объявление отмечено как неактуальное' : 'Объявление отмечено как актуальное',
    });
  };

  const formattedDate = formatDistanceToNow(new Date(announcement.createdAt), {
    addSuffix: true,
    locale: ru,
  });

  return (
    <Card className={`overflow-hidden transition-all duration-200 ${!active ? 'opacity-70' : ''}`}>
      <CardHeader className="pb-2">
        <div className="flex justify-between items-start">
          <CardTitle className="text-lg">{announcement.title}</CardTitle>
          <Badge className={getTypeColor(announcement.type)}>
            {getTypeText(announcement.type)}
          </Badge>
        </div>
        <CardDescription className="flex items-center space-x-1">
          <CalendarDays className="w-3 h-3" />
          <span>{formattedDate}</span>
        </CardDescription>
      </CardHeader>
      <CardContent>
        <p className="text-sm">{announcement.description}</p>
        
        {announcement.location && (
          <div className="flex items-center mt-2 text-xs text-gray-500">
            <MapPin className="h-3 w-3 mr-1" />
            <span>{announcement.location}</span>
          </div>
        )}
        
        {announcement.price && (
          <div className="flex items-center mt-2 text-xs font-medium">
            <Tag className="h-3 w-3 mr-1" />
            <span>{announcement.price}₽</span>
          </div>
        )}
      </CardContent>
      <CardFooter className="flex justify-between pt-2 border-t">
        <div className="text-xs text-gray-500">
          {announcement.author.name}
        </div>
        <div className="flex space-x-2">
          <Button size="sm" variant="ghost" onClick={toggleActive}>
            {active ? 'Неактуально' : 'Актуально'}
          </Button>
          <Button size="sm" variant="secondary" onClick={handleMessage}>
            <MessageSquare className="h-3 w-3 mr-1" />
            Написать
          </Button>
        </div>
      </CardFooter>
    </Card>
  );
};

export default AnnouncementCard;