import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { ThumbsUp } from 'lucide-react';
import { useState } from 'react';
import { formatDistanceToNow } from 'date-fns';
import { ru } from 'date-fns/locale';
import { cn } from '@/lib/utils';
import { Button } from '@/components/ui/button';
import { currentUser } from '@/data/mockData';

const ChatMessage = ({ message }) => {
  const [likes, setLikes] = useState(message.likes);
  const [hasLiked, setHasLiked] = useState(false);

  const isCurrentUser = message.author.id === currentUser.id;

  const handleLike = () => {
    if (!hasLiked) {
      setLikes(likes + 1);
      setHasLiked(true);
    } else {
      setLikes(likes - 1);
      setHasLiked(false);
    }
  };

  const formattedDate = formatDistanceToNow(new Date(message.createdAt), {
    addSuffix: true,
    locale: ru,
  });

  return (
    <div
      className={cn(
        'flex mb-4',
        isCurrentUser ? 'justify-end' : 'justify-start'
      )}
    >
      <div
        className={cn(
          'max-w-[80%] flex',
          isCurrentUser ? 'flex-row-reverse' : 'flex-row'
        )}
      >
        {!message.isAnonymous && (
          <Avatar className={cn('h-8 w-8', isCurrentUser ? 'ml-2' : 'mr-2')}>
            <AvatarImage src={message.author.avatar} alt={message.author.name} />
            <AvatarFallback>
              {message.isAnonymous ? 'A' : message.author.name.substring(0, 2)}
            </AvatarFallback>
          </Avatar>
        )}
        {message.isAnonymous && (
          <Avatar className={cn('h-8 w-8', isCurrentUser ? 'ml-2' : 'mr-2')}>
            <AvatarFallback>A</AvatarFallback>
          </Avatar>
        )}

        <div>
          <div
            className={cn(
              'rounded-lg p-3',
              isCurrentUser
                ? 'bg-primary text-white rounded-tr-none'
                : 'bg-secondary rounded-tl-none'
            )}
          >
            <div className="text-xs mb-1">
              {message.isAnonymous ? 'Аноним' : message.author.name}
            </div>
            <p className="text-sm">{message.text}</p>
          </div>
          <div
            className={cn(
              'flex items-center mt-1 text-xs text-gray-500',
              isCurrentUser ? 'justify-end' : 'justify-start'
            )}
          >
            <span>{formattedDate}</span>
            <div className="mx-2">•</div>
            <Button
              variant="ghost"
              size="sm"
              className="h-6 px-1 flex items-center space-x-1"
              onClick={handleLike}
            >
              <ThumbsUp className={cn('h-3 w-3', hasLiked ? 'text-primary fill-primary' : '')} />
              <span>{likes}</span>
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChatMessage;