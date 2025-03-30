import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Coins } from 'lucide-react';

const UserStatsCard = ({ user, rank }) => {
  return (
    <Card className="overflow-hidden">
      <CardHeader className="pb-2">
        <CardTitle className="text-lg">
          {rank ? `#${rank} ` : ''}
          {user.name}
        </CardTitle>
      </CardHeader>
      <CardContent>
        <div className="flex items-center space-x-4">
          <Avatar className="h-16 w-16">
            <AvatarImage src={user.avatar} alt={user.name} />
            <AvatarFallback>{user.name.substring(0, 2)}</AvatarFallback>
          </Avatar>
          <div className="space-y-2">
            <div className="flex items-center">
              <Coins className="h-5 w-5 text-dormcoin mr-2" />
              <span className="font-bold">{user.dormCoin}</span>
              <span className="ml-1 text-gray-500 text-sm">ОбщагаCoin</span>
            </div>
            {user.badges && user.badges.length > 0 && (
              <div className="flex flex-wrap gap-1">
                {user.badges.map((badge) => (
                  <div
                    key={badge.id}
                    className="flex items-center space-x-1 bg-secondary rounded-full px-2 py-0.5 text-xs"
                    title={badge.description}
                  >
                    <span>{badge.icon}</span>
                    <span>{badge.name}</span>
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default UserStatsCard;