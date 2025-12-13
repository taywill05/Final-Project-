jest.mock('../authApi');
const authApiModule = require('../authApi');
const api = authApiModule.default || authApiModule;
const { createMoodPost, getMoodPostsForUser } = require('../moodApi');

describe('moodApi', () => {
  beforeEach(() => {
    jest.resetAllMocks();
    api.post = jest.fn();
    api.get = jest.fn();
  });

  it('createMoodPost posts and returns data', async () => {
    const fake = { id: 1, username: 'u', mood: 'joy', note: 'hi', emoji: ':)' };
    api.post.mockResolvedValue({ data: fake });

    const out = await createMoodPost('u', 'joy', 'hi', ':)');
    expect(api.post).toHaveBeenCalledWith('/mood-posts', {
      username: 'u',
      mood: 'joy',
      note: 'hi',
      emoji: ':)'
    });
    expect(out).toEqual(fake);
  });

  it('getMoodPostsForUser calls get and returns array', async () => {
    const fake = [{ id: 2, username: 'green', mood: 'x' }];
    api.get.mockResolvedValue({ data: fake });
    const res = await getMoodPostsForUser('green');
    expect(api.get).toHaveBeenCalledWith('/mood-posts/user/green');
    expect(res).toEqual(fake);
  });
});
