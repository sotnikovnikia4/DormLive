/**
 * @typedef {Object} User
 * @property {string} id - Уникальный идентификатор пользователя.
 * @property {string} name - Имя пользователя.
 * @property {number} dormCoin - Количество "ОбщагаCoin".
 * @property {string} [roomNumber] - Номер комнаты (опционально).
 * @property {number} [floor] - Этаж (опционально).
 * @property {Badge[]} badges - Список значков пользователя.
 * @property {string} [avatar] - URL аватара (опционально).
 */

/**
 * @typedef {Object} Badge
 * @property {string} id - Уникальный идентификатор значка.
 * @property {string} name - Название значка.
 * @property {string} description - Описание значка.
 * @property {string} icon - Иконка значка.
 */

/**
 * @typedef {Object} Announcement
 * @property {string} id - Уникальный идентификатор объявления.
 * @property {string} title - Заголовок объявления.
 * @property {string} description - Описание объявления.
 * @property {'sell' | 'search' | 'event'} type - Тип объявления.
 * @property {number} [price] - Цена (опционально, только для типа 'sell').
 * @property {string} [location] - Местоположение (опционально).
 * @property {{ id: string, name: string }} author - Автор объявления.
 * @property {string} createdAt - Дата создания объявления.
 * @property {boolean} isActive - Активно ли объявление.
 */

/**
 * @typedef {Object} ChatRoom
 * @property {string} id - Уникальный идентификатор чата.
 * @property {string} name - Название чата.
 * @property {string} description - Описание чата.
 * @property {string} icon - Иконка чата.
 * @property {number} participants - Количество участников чата.
 */

/**
 * @typedef {Object} ChatMessage
 * @property {string} id - Уникальный идентификатор сообщения.
 * @property {string} text - Текст сообщения.
 * @property {{ id: string, name: string, avatar?: string }} author - Автор сообщения.
 * @property {boolean} isAnonymous - Является ли сообщение анонимным.
 * @property {number} likes - Количество лайков.
 * @property {string} createdAt - Дата создания сообщения.
 */

/**
 * @typedef {Object} EmergencyNotification
 * @property {string} id - Уникальный идентификатор уведомления.
 * @property {string} title - Заголовок уведомления.
 * @property {string} description - Описание уведомления.
 * @property {string} createdAt - Дата создания уведомления.
 * @property {boolean} isRead - Прочитано ли уведомление.
 */

/**
 * @typedef {Object} GameScore
 * @property {string} id - Уникальный идентификатор записи.
 * @property {string} userId - Идентификатор пользователя.
 * @property {string} userName - Имя пользователя.
 * @property {number} score - Количество очков.
 * @property {string} date - Дата записи.
 */