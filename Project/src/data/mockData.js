// Mock User
export const currentUser = {
  id: "12345",
  name: "Иван Иванов",
  dormCoin: 156,
  roomNumber: "42",
  floor: 4,
  badges: [
    {
      id: "badge1",
      name: "Новичок",
      description: "Добро пожаловать в Общагу!",
      icon: "🏠"
    },
    {
      id: "badge2",
      name: "Помощник",
      description: "Помог 5 студентам",
      icon: "🤝"
    }
  ],
  avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=12345"
};

// Mock Announcements
export const announcements = [
  {
    id: "ann1",
    title: "Продаю чайник",
    description: "Почти новый электрический чайник, 2л. Использовался меньше месяца.",
    type: "sell",
    price: 50,
    location: "3 этаж, комната 301",
    author: {
      id: "user2",
      name: "Анна Петрова"
    },
    createdAt: "2023-09-15T10:30:00",
    isActive: true
  },
  {
    id: "ann2",
    title: "Ищу соседа",
    description: "Ищу соседа в комнату 421. Тихий, не курю, учусь на 2 курсе ИТ факультета.",
    type: "search",
    location: "4 этаж, комната 421",
    author: {
      id: "user3",
      name: "Дмитрий Смирнов"
    },
    createdAt: "2023-09-14T14:20:00",
    isActive: true
  },
  {
    id: "ann3",
    title: "Мафия сегодня в 19:00",
    description: "Собираемся играть в мафию в комнате отдыха на 5 этаже. Приглашаются все желающие!",
    type: "event",
    location: "5 этаж, комната отдыха",
    author: {
      id: "user4",
      name: "Алексей Козлов"
    },
    createdAt: "2023-09-15T09:00:00",
    isActive: true
  },
  {
    id: "ann4",
    title: "Продаю учебники",
    description: "Учебники по математике и физике за 1 курс. Состояние хорошее.",
    type: "sell",
    price: 300,
    location: "2 этаж, комната 215",
    author: {
      id: "user5",
      name: "Мария Иванова"
    },
    createdAt: "2023-09-13T11:45:00",
    isActive: true
  },
  {
    id: "ann5",
    title: "Киновечер",
    description: "В субботу в 20:00 смотрим Властелина Колец в холле 1 этажа. Берите снеки!",
    type: "event",
    location: "1 этаж, холл",
    author: {
      id: "user6",
      name: "Сергей Николаев"
    },
    createdAt: "2023-09-14T16:30:00",
    isActive: true
  }
];

// Mock Chat Rooms
export const chatRooms = [
  {
    id: "chat1",
    name: "4 Этаж",
    description: "Чат для жителей 4 этажа",
    icon: "🏢",
    participants: 45
  },
  {
    id: "chat2",
    name: "Кулинария",
    description: "Делимся рецептами и кулинарными хитростями в условиях общаги",
    icon: "🍳",
    participants: 67
  },
  {
    id: "chat3",
    name: "Настольные игры",
    description: "Обсуждение и организация игровых вечеров",
    icon: "🎲",
    participants: 38
  },
  {
    id: "chat4",
    name: "Обмен вещами",
    description: "Поменяться или одолжить что-то нужное",
    icon: "🔄",
    participants: 120
  },
  {
    id: "chat5",
    name: "Анонимный чат",
    description: "Для жалоб и предложений по улучшению общежития",
    icon: "🎭",
    participants: 210
  }
];

// Mock Chat Messages
export const chatMessages = {
  "chat1": [
    {
      id: "msg1",
      text: "У кого-нибудь есть зарядка для Macbook Pro?",
      author: {
        id: "user7",
        name: "Ольга",
        avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Ольга"
      },
      isAnonymous: false,
      likes: 0,
      createdAt: "2023-09-15T10:30:00"
    },
    {
      id: "msg2",
      text: "У меня есть, комната 412",
      author: {
        id: "user8",
        name: "Никита",
        avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Никита"
      },
      isAnonymous: false,
      likes: 2,
      createdAt: "2023-09-15T10:32:00"
    },
    {
      id: "msg3",
      text: "Завтра вечеринка в 408 комнате! Приглашаются все с 4 этажа",
      author: {
        id: "user9",
        name: "Максим",
        avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Максим"
      },
      isAnonymous: false,
      likes: 15,
      createdAt: "2023-09-15T11:20:00"
    }
  ],
  "chat2": [
    {
      id: "msg4",
      text: "Кто-нибудь знает, как приготовить нормальный ужин на плитке с одной конфоркой?",
      author: {
        id: "user10",
        name: "Екатерина",
        avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Екатерина"
      },
      isAnonymous: false,
      likes: 8,
      createdAt: "2023-09-14T18:30:00"
    },
    {
      id: "msg5",
      text: "Могу поделиться рецептом пасты карбонара в микроволновке!",
      author: {
        id: "user11",
        name: "Антон",
        avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Антон"
      },
      isAnonymous: false,
      likes: 21,
      createdAt: "2023-09-14T18:35:00"
    },
    {
      id: "msg6",
      text: "Антон, поделись пожалуйста! Надоели дошики",
      author: {
        id: "12345",
        name: "Иван Иванов",
        avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=12345"
      },
      isAnonymous: false,
      likes: 5,
      createdAt: "2023-09-14T18:40:00"
    }
  ]
};

// Mock Emergency Notifications
export const emergencyNotifications = [
  {
    id: "emergency1",
    title: "Внимание! Завтра проверка от коменданта",
    description: "Проверка комнат с 10:00 до 14:00. Просьба привести комнаты в порядок.",
    createdAt: "2023-09-15T08:00:00",
    isRead: false
  },
  {
    id: "emergency2",
    title: "Отключение горячей воды",
    description: "С 20 по 25 сентября будет отключена горячая вода в связи с профилактическими работами.",
    createdAt: "2023-09-13T14:30:00",
    isRead: true
  },
  {
    id: "emergency3",
    title: "Изменение правил пропускного режима",
    description: "С 1 октября вводятся новые правила пропуска гостей. Подробности на стенде у входа.",
    createdAt: "2023-09-12T11:15:00",
    isRead: true
  }
];

// Mock Game Scores
export const gameScores = [
  {
    id: "score1",
    userId: "user4",
    userName: "Алексей Козлов",
    score: 1450,
    date: "2023-09-14"
  },
  {
    id: "score2",
    userId: "user8",
    userName: "Никита",
    score: 1200,
    date: "2023-09-15"
  },
  {
    id: "score3",
    userId: "user5",
    userName: "Мария Иванова",
    score: 1050,
    date: "2023-09-13"
  },
  {
    id: "score4",
    userId: "12345",
    userName: "Иван Иванов",
    score: 820,
    date: "2023-09-10"
  },
  {
    id: "score5",
    userId: "user10",
    userName: "Екатерина",
    score: 750,
    date: "2023-09-12"
  }
];

// Top users by dormCoin
export const topUsers = [
  {
    id: "user4",
    name: "Алексей Козлов",
    dormCoin: 487,
    badges: [
      {
        id: "badge3",
        name: "Организатор",
        description: "Организовал 10 мероприятий",
        icon: "🎯"
      },
      {
        id: "badge4",
        name: "Легенда общаги",
        description: "100+ лайков в чате",
        icon: "🌟"
      }
    ],
    avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Алексей"
  },
  {
    id: "user7",
    name: "Ольга",
    dormCoin: 352,
    badges: [
      {
        id: "badge5",
        name: "Помощник",
        description: "Помог 5 студентам",
        icon: "🤝"
      }
    ],
    avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Ольга"
  },
  {
    id: "user11",
    name: "Антон",
    dormCoin: 301,
    badges: [
      {
        id: "badge6",
        name: "Шеф-повар",
        description: "Поделился 10 рецептами",
        icon: "👨‍🍳"
      }
    ],
    avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Антон"
  }
];