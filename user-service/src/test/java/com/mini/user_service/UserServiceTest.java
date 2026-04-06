class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUsers() {

        List<UserResponse> users = Arrays.asList(
                new UserResponse(),
                new UserResponse()
        );

        when(userService.getAllUsers()).thenReturn(users);

        List<UserResponse> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }
}