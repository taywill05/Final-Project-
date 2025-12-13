const { saveVibeCheck } = require('../vibeApi');
const { apiSend } = require('../authApi');

jest.mock('../authApi');

describe('vibeApi', () => {
  beforeEach(() => {
    jest.resetAllMocks();
    global.localStorage.clear();
  });

  it('saveVibeCheck throws when no username in localStorage', async () => {
    await expect(saveVibeCheck({ questions: ['q'], answers: ['a'], vibeResult: {} }))
      .rejects.toThrow('No username found. Please log in.');
  });

  it('saveVibeCheck calls apiSend when username exists', async () => {
    jest.spyOn(Storage.prototype, 'getItem').mockImplementation((k) => {
      if (k === 'username') return 'testUser';
      return null;
    });

    apiSend.mockResolvedValue({ success: true });

    const vibeResult = { vibeName: 'relaxed', emoji: '😌', quote: 'Keep it up' };
    const questions = ['q1', 'q2'];
    const answers = ['a1', 'a2'];

    const res = await saveVibeCheck({ questions, answers, vibeResult });
    expect(apiSend).toHaveBeenCalledWith('/api/vibe-checks', 'post', {
      username: 'testUser', questions, answers,
      vibeName: 'relaxed', emoji: '😌', quote: 'Keep it up'
    });
    expect(res).toEqual({ success: true });
  });

});
